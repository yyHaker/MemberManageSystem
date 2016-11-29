package cn.com.shiro.entity.extend;

import java.util.List;
import java.util.Set;

import cn.com.shiro.entity.Permission;
import cn.com.shiro.entity.Role;
import cn.com.shiro.entity.User;

public class UserExtend {
	private User user;
	private List<Role> roles;
	private List<Permission> permissions;
	private Set<String> rolesString;
	private Set<String> permissionsString;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	public Set<String> getRolesString() {
		return rolesString;
	}
	public void setRolesString(Set<String> rolesString) {
		this.rolesString = rolesString;
	}
	public Set<String> getPermissionsString() {
		return permissionsString;
	}
	public void setPermissionsString(Set<String> permissionsString) {
		this.permissionsString = permissionsString;
	}
}
