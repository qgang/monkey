package job;

import job.logger.JobEmailTrace;
import job.util.IpUtil;
import job.util.LogUtil;
import job.util.RetryType;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by gang.qin on 2015/12/17.
 */
public abstract class BaseJob {
    protected static Logger BASE_LOG;
    protected static Logger LOG;
    protected JobEmailTrace trace;
    private static RetryType retryType = RetryType.RETRY_ZORE;
    private boolean needSendEmail = false;


    protected abstract boolean execute(String[] args);
    protected abstract String getMailReceivers();
    protected abstract String getTitle();
    protected abstract String getLogFolder();
    protected abstract String getSummary();

    protected boolean retry (String args[]) {
        return false;
    }

    protected static BaseJob init(String[] xmlPath, RetryType type) {
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
        retryType = type;
        return getBaseJob(context);
    }

    protected static BaseJob init(String xmlPath, RetryType type){
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
        retryType = type;
        return getBaseJob(context);
    }

    public void setNeedSendEmail(boolean needSendEmail) {
        this.needSendEmail = needSendEmail;
    }

    public void run (String[] args) {
        String msg = null;
        try {
            LOG = LogUtil.getLogger(getLogFolder(), this.getClass().getSimpleName());
            beforExcute(); // 执行前的准备

            msg = "the input args is : " + (args == null ? "[]" : args.toString());
            LOG.info(msg);
            trace.info(msg);

            // 执行
            boolean result = false;
            try {
                result = execute(args);
            } catch (Throwable e) {
                msg = "Job Failed";
                LOG.error(msg, e);
                trace.error(msg);
            }

            // 处理重试
            int retryCount = 0;
            if (!result) {
                switch (retryType) {
                    case RETRY_ZORE: break;
                    case RETRY_THREE: retryCount = 3; break;
                    case RETRY_FIVE: retryCount = 5; break;
                    default: break;
                }
            }
            for (int i = 0; i < retryCount; i++) {
                try {
                    if (retry(args)) {
                        break;
                    }
                } catch (Throwable e) {
                    msg = "Job retry failed, retry time is : " + i;
                    LOG.error(msg, e);
                    trace.error(msg);
                }
            }
            afterExcute(); // 执行后的处理
        } catch (Throwable e) {

        }
    }

    private void beforExcute() {
        trace.setTitle(getTitle());
        try {
            String emailReceivers = getMailReceivers(); // 可以放在lion配置上 ConfigCache.getInstance().getProperty(getMailReceivers())
            trace.setReceivers(emailReceivers);
        } catch (Exception e) {
            LOG.warn("receivers init failed.", e);
        }
        trace.begin();
        trace.info("Job summery is : " + getSummary());
        trace.info("machine ip is : " + IpUtil.getLocalIpv4Address());
    }

    private void afterExcute() {
        trace.end();
        if (needSendEmail) {
            try {
                trace.send();
            } catch (EmailException e) {
                LOG.warn("send Email failed, email content is " + trace.getContent(), e);
            }
        }
    }

    private static BaseJob getBaseJob(ApplicationContext context) {
        Map beenMap = context.getBeansOfType(BaseJob.class);
        if (beenMap.size() != 1) {
            String msg = "must match single one bean!";
            LOG.error(msg);
            throw  new RuntimeException(msg);
        }
        return (BaseJob)(beenMap.values().iterator().next());
    }
}
