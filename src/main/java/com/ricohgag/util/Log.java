package com.ricohgag.util;

/**
 * @author ricohgag
 * @date 2019/2/16 16:55
 */
public class Log {
    private Integer logCount = 0;

    public void info(String str){
        System.err.println((logCount++)+" : "+str);
    }

    public static void main(String[] args){
        String str = "--123";
        new Log().info(""+str.indexOf("--!"));
    }
}
