package test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int a = Integer.MAX_VALUE;

        System.out.println(BigDecimal.valueOf(a * a));
        System.out.println(BigDecimal.valueOf((long)a * a));
    }
}

