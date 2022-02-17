package com.github.alexscari;

import java.io.IOException;
import java.util.Properties;

/**
 * @author wusd
 */
public class ReadProp {

    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        prop.load(ReadProp.class.getClassLoader().getResourceAsStream("app.properties"));
        String o = prop.getProperty("java.home");
        System.out.print(o);
    }

}
