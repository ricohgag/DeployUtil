package com.ricohgag.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ricohgag.util.FileUtils;

/**
 * @author ricohgag
 * @date 2019/2/17 13:08
 */
public class DeployConfig {
    private String address;

    private Integer port;

    private String username;

    private String password;

    private String basepath;

    public DeployConfig(){

    }

    public DeployConfig(String filePath){
        String configStr = FileUtils.getFilePath(filePath);
        JSONObject jsonObj = JSON.parseObject(configStr);

        this.address = jsonObj.getString("address");
        this.port = jsonObj.getInteger("port");
        this.username = jsonObj.getString("username");
        this.password = jsonObj.getString("password");
        this.basepath = jsonObj.getString("basepath");

    }

    public String getAddress() {
        return address;
    }

    public DeployConfig setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public DeployConfig setPort(Integer port) {
        this.port = port;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DeployConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DeployConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getBasepath() {
        return basepath;
    }

    public DeployConfig setBasepath(String basepath) {
        this.basepath = basepath;
        return this;
    }

    @Override
    public String toString() {
        return "DeployConfig{" +
                "address='" + address + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", basepath='" + basepath + '\'' +
                '}';
    }
}
