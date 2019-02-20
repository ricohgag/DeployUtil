package com.ricohgag.main;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ricohgag.action.SFtpsFile;
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
        String[] projectUrls = projectStr.split("\\r");
        System.out.println("length: "+projectUrls.length);

        DeployConfig config = new DeployConfig("D:\\app\\deploy\\config.json");
        log.info("测试json: "+config.getAddress());

        for(String url:projectUrls){

            System.err.println("--------------标记---------------");
            System.err.println("url: "+url);

            String result = SFtpsFile.newFtpUpload(config.getAddress(), config.getPort(), config.getUsername(),
                    config.getPassword(), config.getBasepath(), "", "", url);
        }



    }




}
