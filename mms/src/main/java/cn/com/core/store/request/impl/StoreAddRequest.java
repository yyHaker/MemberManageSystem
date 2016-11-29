package cn.com.core.store.request.impl;

import cn.com.core.store.request.StoreRequest;

/**
 * Created by Zhang Siqi on 2016/11/6.
 */

public class StoreAddRequest implements StoreRequest {

    private String id;

    private String name;

    private String city;

    private String address;

    private String telephone;
    
    private String managerId;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
}
