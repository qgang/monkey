package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date start = c.getTime();

        c.add(Calendar.DAY_OF_MONTH, 1);
        Date end = c.getTime();
        long diff = Math.abs(start.getTime() - end.getTime());
        System.out.print("\nstart:" + sdf.format(start)
                        +"\nend:" + sdf.format(end)
                        +"\ndiff:" + diff
                        +"\nmaxv:" + Long.MAX_VALUE
                        +"\n" + (Long.MAX_VALUE > diff)
                        +"\nmaxd:" + Long.MAX_VALUE/1000/60/60/24/365
                        +"\n");
    }
}

