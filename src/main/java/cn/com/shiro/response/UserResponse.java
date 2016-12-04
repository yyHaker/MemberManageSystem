package cn.com.shiro.response;

import cn.com.core.manager.request.ManagerJson;
import cn.com.core.member.request.MemberJson;
import cn.com.core.root.Root;

public class UserResponse {
	private String role;
	private Root root; 
	private ManagerJson managerJson;
	private MemberJson memberJson;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Root getRoot() {
		return root;
	}
	public void setRoot(Root root) {
		this.root = root;
	}
	public ManagerJson getManagerJson() {
		return managerJson;
	}
	public void setManagerJson(ManagerJson managerJson) {
		this.managerJson = managerJson;
	}
	public MemberJson getMemberJson() {
		return memberJson;
	}
	public void setMemberJson(MemberJson memberJson) {
		this.memberJson = memberJson;
	}
}
