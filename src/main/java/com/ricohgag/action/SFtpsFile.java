package com.ricohgag.action;

import com.jcraft.jsch.*;
import com.ricohgag.util.Log;
import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SFtpsFile {

    /**
     * 上传文件到ftp服务器
     * @param host
     * @param port
     * @param username
     * @param password
     * @param basePath
     * @param filePath
     * @param filename
     * @param input
     * @return
     * @throws IOException
     */
    public static String newFtpUpload(String host, int port, String username, String password, String basePath,
                                      String pathPrefix, String filePath, String filename, InputStream input) {
        Log log = new Log();
        log.info(basePath+filePath);

        ChannelSftp sftp = null;
        //判断是否成功的boolean值
        boolean success = false;
        //返回值
        String path = "";
        //Session对象
        Session session = null;
        try {
            //创建JSch对象
            JSch jSch = new JSch();
            //调用JSch对象的getSession方法（参数是服务器的访问用户名,服务器访问路径,服务器的端口号）给session赋值
            session = jSch.getSession(username, host, port);
            //给session对象设置密码参数也就是你的服务器访问的密码
            session.setPassword(password);
            //创建参数
            Properties sshConfig = new Properties();
            //给参数对象赋值，这里解决
            sshConfig.put("StrictHostKeyChecking", "no");
            //这里设置参数给session主要解决把kerberos认证方式去掉，如果不写这一步走到这里你需要向控制台输入你的	    	    kerberos用户名和口令，如果你的项目环境没有涉及到kerberos应该是不用设置
            session.setConfig(
                    "PreferredAuthentications",
                    "publickey,keyboard-interactive,password");
            //把参数对象给session对象注入
            session.setConfig(sshConfig);
            //打开session连接
            session.connect(15000);
            //使用session对象连接服务器
            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            //使用ChannelSftp对象进行使用命令
            //进入需要进入的路径
            sftp.cd(basePath);

            checkDirExist(sftp, filePath);
//            sftp.cd(filePath);
            log.info(sftp.pwd());
            //进行文件上传
            sftp.put(input, filename);
            //设置上传成功
            success = true;

            doShell((ChannelShell) session.openChannel("shell"));

        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (Exception e){
          e.printStackTrace();
        } finally {
            //关闭连接
            if (sftp != null) {
                if (sftp.isConnected()) {
                    sftp.disconnect();
                }
            }
            //关闭session
            if (session != null) {
                if (session.isConnected()) {
                    session.disconnect();
                }
            }
        }
        //判断是否成功
        if(success){
            log.info("上传成功");
            //设置返回路径为访问路径（你的服务器访问路径+新文件名）
            path = pathPrefix + filePath + filename;
        }
        return path;
    }

    private static void doShell(ChannelShell shell) {

        try {
            shell.connect();

            InputStream is = shell.getInputStream();
            PrintWriter pw = new PrintWriter(shell.getOutputStream());

            List<String> cmdList = new ArrayList();
            cmdList.add("ls\r");
            cmdList.add("exit\r");

            for(String cmd:cmdList){
                System.err.println("cmd: "+cmd);
                pw.println(cmd);
            }

            pw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String msg = null;
            while ((msg=br.readLine())!=null){
                System.out.println("msg: "+msg);
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        }

    }


    /**
     * 判断目录是否存在
     * @param sftp
     * @param fileDir
     * @return
     */
    private static void checkDirExist(ChannelSftp sftp, String fileDir) throws Exception {

        //循环判断子目录文件夹是否存在，不存在即创建
        String[] folders = fileDir.split( "/" );
        for ( String folder : folders ) {
            if ( folder.length() > 0 ) {
                try {
                    sftp.cd( folder );
                }
                catch ( SftpException e ) {
                    sftp.mkdir( folder );
                    sftp.cd( folder );
                }
            }
        }



    }
}