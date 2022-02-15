package com.github.alexscari;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @author wusd
 */
public class CounterPrinter {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Calendar.getInstance().getTime() + ":" + i);
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
