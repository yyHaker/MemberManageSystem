package cn.com.core.member.entity;

import java.util.Date;

public class Member {
    private String id;

    private String username;

    private String email;

    private String sex;

    private Date birthday;

    private String telephone;

    private String storeId;

    private String level;

    private Integer points;

    private Integer flightCount;

    private Date lastVisitedTime;

    private Integer flightTime;

    private Float consumptionSum;

    private Float balance;

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getFlightCount() {
        return flightCount;
    }

    public void setFlightCount(Integer flightCount) {
        this.flightCount = flightCount;
    }

    public Date getLastVisitedTime() {
        return lastVisitedTime;
    }

    public void setLastVisitedTime(Date lastVisitedTime) {
        this.lastVisitedTime = lastVisitedTime;
    }

    public Integer getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Integer flightTime) {
        this.flightTime = flightTime;
    }

    public Float getConsumptionSum() {
        return consumptionSum;
    }

    public void setConsumptionSum(Float consumptionSum) {
        this.consumptionSum = consumptionSum;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
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