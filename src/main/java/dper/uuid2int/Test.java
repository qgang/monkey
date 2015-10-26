package dper.uuid2int;

import Util.IPV4Util;

/**
 * Created by gang.qin on 2015/9/2.
 */
public class Test {

    private Boolean UUIDProduceMain() {
//        String sourcePath1 = "D:\\dianping\\event-2015-09-01\\rs-mq01.nh__rs-mq.eda_info_daily.log.2015-08-26";
//        String sourcePath2 = "D:\\dianping\\event-2015-09-01\\rs-mq01.nh__rs-mq.eda_info_daily.log.2015-08-27";
//        String sourcePath3 = "D:\\dianping\\event-2015-09-01\\rs-mq01.nh__rs-mq.eda_info_daily.log.2015-08-28";
//        String sourcePath4 = "D:\\dianping\\event-2015-09-01\\rs-mq01.nh__rs-mq.eda_info_daily.log.2015-08-29";
//        String sourcePath5 = "D:\\dianping\\event-2015-09-01\\rs-mq01.nh__rs-mq.eda_info_daily.log.2015-08-30";
//        String sourcePath6 = "D:\\dianping\\event-2015-09-01\\rs-mq01.nh__rs-mq.eda_info_daily.log.2015-08-31";
//        String sourcePath7 = "D:\\dianping\\event-2015-09-01\\rs-mq01.nh__rs-mq.eda_info_daily.log.2015-09-01";
//        String targetPath = "D:\\dianping\\event-2015-09-01\\uuidtemp.txt";
//        UUIDProduce uuidProduce = new UUIDProduce();
//        uuidProduce.getUUID(sourcePath1, targetPath);
//        uuidProduce.getUUID(sourcePath2, targetPath);
//        uuidProduce.getUUID(sourcePath3, targetPath);
//        uuidProduce.getUUID(sourcePath4, targetPath);
//        uuidProduce.getUUID(sourcePath5, targetPath);
//        uuidProduce.getUUID(sourcePath6, targetPath);
//        uuidProduce.getUUID(sourcePath7, targetPath);
//        return true;

        String sourcePath1 = "D:\\dianping\\event-2015-09-01\\rs-mq02.nh__rs-mq.eda_info_daily.log.2015-08-26";
        String sourcePath2 = "D:\\dianping\\event-2015-09-01\\rs-mq02.nh__rs-mq.eda_info_daily.log.2015-08-27";
        String sourcePath3 = "D:\\dianping\\event-2015-09-01\\rs-mq02.nh__rs-mq.eda_info_daily.log.2015-08-28";
        String sourcePath4 = "D:\\dianping\\event-2015-09-01\\rs-mq02.nh__rs-mq.eda_info_daily.log.2015-08-29";
        String sourcePath5 = "D:\\dianping\\event-2015-09-01\\rs-mq02.nh__rs-mq.eda_info_daily.log.2015-08-30";
        String sourcePath6 = "D:\\dianping\\event-2015-09-01\\rs-mq02.nh__rs-mq.eda_info_daily.log.2015-08-31";
        String sourcePath7 = "D:\\dianping\\event-2015-09-01\\rs-mq02.nh__rs-mq.eda_info_daily.log.2015-09-01";
        String targetPath = "D:\\dianping\\event-2015-09-01\\uuidtemp.txt";
        UUIDProduce uuidProduce = new UUIDProduce();
        uuidProduce.getUUID(sourcePath1, targetPath);
        uuidProduce.getUUID(sourcePath2, targetPath);
        uuidProduce.getUUID(sourcePath3, targetPath);
        uuidProduce.getUUID(sourcePath4, targetPath);
        uuidProduce.getUUID(sourcePath5, targetPath);
        uuidProduce.getUUID(sourcePath6, targetPath);
        uuidProduce.getUUID(sourcePath7, targetPath);
        return true;
    }

    private Boolean UUIDDealMain() {
        String sourcePath1 = "D:\\dianping\\event-2015-09-01\\uuidtemp.txt";
        String targetPath = "D:\\dianping\\event-2015-09-01\\uuid.txt";
        UUIDDeal uUIDDeal = new UUIDDeal();
        uUIDDeal.execute(sourcePath1, targetPath);
        return true;
    }

    private Boolean UUIDToInTestMain() {
        String sourcePath = "D:\\dianping\\event-2015-09-01\\uuid.txt";
        String targetPath = "D:\\dianping\\event-2015-09-01\\uuidint.txt";
        UUIDToIntTest uuidToIntTest = new UUIDToIntTest();
        uuidToIntTest.getUUID(sourcePath, targetPath);
        return true;
    }

    private Boolean UUIDIntDealMain() {
        String sourcePath = "D:\\dianping\\event-2015-09-01\\uuidint.txt";
        String targetPath = "D:\\dianping\\event-2015-09-01\\uuidintfinal.txt";
        UUIDIntDeal uuidIntDeal = new UUIDIntDeal();
        uuidIntDeal.execute(sourcePath, targetPath);
        return true;
    }

    public static void main(String[] args) {
        Test test = new Test();
//        test.putout(test.UUIDProduceMain(), "UUIDProduceMain uuidtemp.txt");
//        test.putout(test.UUIDDealMain(), "UUIDDealMain uuid.txt");
//        test.putout(test.UUIDToInTestMain(), "UUIDToInTestMain uuidint.txt");
//        test.putout(test.UUIDIntDealMain(), "UUIDIntDealMain uuidintfinal.txt");

        System.out.println(IPV4Util.int2IP(176175629));
    }

    private void putout(Boolean bool, String out) {
        if (bool) {
            System.out.println(out);
        }
    }
}
