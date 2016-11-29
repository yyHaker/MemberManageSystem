package cn.com.core.member.request;

/**
 * MemberJson
 *
 * @author Le Yuan
 * @date 2016/10/22
 */
public class MemberJson {

    private String id;

    private String username;

    private String password;

    private String sex;

    private String birthday; //用字符串接收，后面在转换为java Date类型

    private String telephone;

    private String storeId;

    private String level;

    private Integer points;

    private Integer flightCount;

    private String lastVisitedTime;

    private Integer flightTime;

    private Float consumptionSum;

    private Float balance;

    private String createTime;//用字符串接收，后面在转换为java Date类型

    private String updateTime;//用字符串接收，后面在转换为java Date类型

    private int beginPageIndex;//分页开始页数

    private int pageSize; //分页大小

    private int pageNum;//分页数量

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getBeginPageIndex() {
        return beginPageIndex;
    }

    public void setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getLastVisitedTime() {
        return lastVisitedTime;
    }

    public void setLastVisitedTime(String lastVisitedTime) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
