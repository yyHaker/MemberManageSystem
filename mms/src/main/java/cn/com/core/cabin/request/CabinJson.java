package cn.com.core.cabin.request;


/**
 * CabinJson
 *
 * @author Le Yuan
 * @date 2016/11/6
 */
public class CabinJson {
    private String id;

    private String number;

    private String name;

    private String storeId;

    private Float rent;

    private String  createTime;  //用String接受

    private String  updateTime; //用String接受

    private int beginPageIndex;//分页开始页数

    private int pageSize; //分页大小

    public CabinJson() {
    }

    public CabinJson(String id, String number, String name, String storeId, Float rent, String createTime, String updateTime) {
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
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Float getRent() {
        return rent;
    }

    public void setRent(Float rent) {
        this.rent = rent;
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
}
