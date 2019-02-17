package com.ricohgag.pojo;



/**
 * @author ricohgag
 * @date 2019/2/16 11:52
 */
public class DeployProject {
    private Integer id;

    private String name;

    private String packAddressList;

    public Integer getId() {
        return id;
    }

    public DeployProject setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DeployProject setName(String name) {
        this.name = name;
        return this;
    }

    public String getPackAddressList() {
        return packAddressList;
    }

    public DeployProject setPackAddressList(String packAddressList) {
        this.packAddressList = packAddressList;
        return this;
    }

    @Override
    public String toString() {
        return "{ id: "+id+", name: "+name+", packAddresss: "+packAddressList+" }";
    }
}
