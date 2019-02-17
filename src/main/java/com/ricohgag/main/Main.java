package com.ricohgag.main;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ricohgag.pojo.DeployConfig;
import com.ricohgag.util.FileUtils;
import com.ricohgag.util.Log;
import java.io.*;

/**
 * @author ricohgag
 * @date 2019/2/16 16:04
 */
public class Main {
    static Log log = new Log();
    public static void main(String[] args) throws Exception{

        String projectStr = FileUtils.getFilePath("D:\\app\\deploy\\project.json");
        String[] projectUrls = projectStr.split("\r");

        for(String url:projectUrls){
            InputStream inputStream = FileUtils.getFileInputStream(url);

        }

        DeployConfig deployConfig = new DeployConfig("D:\\app\\deploy\\config.json");

        log.info("测试json: "+deployConfig.getAddress());

    }



    private static void upload() {

    }



}
