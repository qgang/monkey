package test;

import java.util.Date;

/**
 * Created by steelqin on 17/2/15.
 */
public class Sub extends Super {
    private final Date date;
    public Sub() {
        date = new Date();
    }

    @Override
    public void overrideme() {
        System.out.println(date.getTime());
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideme();
    }
}
