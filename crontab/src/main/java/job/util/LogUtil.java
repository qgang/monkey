package job.util;


import org.apache.log4j.*;

import java.io.IOException;

/**
 * Created by gang.qin on 2015/12/18.
 */
public class LogUtil {
    public static Logger getLogger(String categroy, String name) {
        Logger logger = Logger.getLogger(name);
        logger.removeAllAppenders();
        logger.setAdditivity(false);

        FileAppender appender = null;
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern("[%p][%d{yyyy-MM-dd HH:mm:ss}] %m%n");
        try {
            appender = new DailyRollingFileAppender(layout, String.format("/data/applogs/%s/%s.log",categroy,name), "yyyy-MM-dd");
            appender.setEncoding("UTF-8");
            appender.activateOptions();
        } catch (IOException e) {
            throw new RuntimeException("LogUtil fail.", e);
        }
        logger.addAppender(appender);
        logger.setLevel(Level.INFO);

        return logger;
    }
}
