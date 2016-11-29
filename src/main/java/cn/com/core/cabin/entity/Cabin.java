package cn.com.core.cabin.entity;

import java.util.Date;

public class Cabin {
    private String id;

    private String number;

    private String name;

    private String storeId;

    private Float rent;

    private Date createTime;

    private Date updateTime;

    public Cabin() {
    }

    public Cabin(String id, String number, String name, String storeId, Float rent, Date createTime, Date updateTime) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.storeId = storeId;
        this.rent = rent;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }

    public Float getRent() {
        return rent;
    }

    public void setRent(Float rent) {
        this.rent = rent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}