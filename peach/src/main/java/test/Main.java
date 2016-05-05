package test;

import com.google.gson.Gson;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(Arrays.asList(67360)));
    }
}

