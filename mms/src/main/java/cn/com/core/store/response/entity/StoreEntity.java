package cn.com.core.store.response.entity;

import cn.com.core.store.Constants;
import cn.com.core.store.entity.Store;
import cn.thinking.common.util.Tools;

public class StoreEntity {
    private String id;

    private String number;

    private String name;

    private String city;

    
    private String managerId;

    private String address;

    private String telephone;

    private String flag;

    
    private String createTime;
    private String updateTime;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	
	public StoreEntity(Store store){
		this.id = store.getId();
		this.number = store.getNumber();
		this.name = store.getName();
		this.city = store.getCity();
		
		this.managerId = store.getManagerId();
		this.address = store.getAddress();
		this.telephone = store.getTelephone();
		this.flag = store.getFlag().equals(Constants.STORE_IS_LOCK) ? "否" : "是";
		
		this.createTime = Tools.date2Str(store.getCreateTime(), "yyyy-MM-dd");
		this.updateTime = Tools.date2Str(store.getUpdateTime(), "yyyy-MM-dd");
	}
}
