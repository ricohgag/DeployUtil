package com.ricohgag.util;

import java.io.*;

/**
 * @author ricohgag
 * @date 2019/2/17 13:18
 */
public class FileUtils {

    /**
     * 根据文件路径读出文件内容为string
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String getFilePath(String filePath){
        File file = new File(filePath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e){
            System.err.println("FileNotFoundException, ErrBy: getFilePath()");
            e.getStackTrace();
        }


        String projectStr = "";
        try {
            String line = "";
            line = br.readLine().trim();


            while (line != null){
                System.out.println("每行: "+line);
                if(line.trim().indexOf("--")!=0){
                    projectStr += line+"\r";
                }

                line = br.readLine();
            }
        } catch (IOException e){
            System.err.println("IOException, ErrBy: getFilePath()");
            e.printStackTrace();
        }


        return projectStr;
    }

    public static InputStream getFileInputStream(String url) throws IOException {
        File file = new File(url);
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        return inputStream;

    }
}
