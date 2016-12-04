package cn.com.shiro.service.imple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.shiro.entity.Permission;
import cn.com.shiro.entity.Role;
import cn.com.shiro.entity.RoleExample;
import cn.com.shiro.entity.RoleToPermissionExample;
import cn.com.shiro.entity.RoleToPermissionKey;
import cn.com.shiro.entity.User;
import cn.com.shiro.entity.UserExample;
import cn.com.shiro.entity.UserToRoleExample;
import cn.com.shiro.entity.UserToRoleKey;
import cn.com.shiro.entity.extend.UserExtend;
import cn.com.shiro.exception.UserExistException;
import cn.com.shiro.mapper.PermissionMapper;
import cn.com.shiro.mapper.RoleMapper;
import cn.com.shiro.mapper.RoleToPermissionMapper;
import cn.com.shiro.mapper.UserMapper;
import cn.com.shiro.mapper.UserToRoleMapper;
import cn.com.shiro.service.UserService;
import cn.com.shiro.utils.PasswordHelper;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class UserServiceImpl implements UserService {
	@Autowired
    private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired 
	private PermissionMapper permissionMapper;
	@Autowired
	private UserToRoleMapper userToRoleMapper;
	@Autowired
	private RoleToPermissionMapper roleToPermissionMapper;
	

	@Override
	public UserExtend getUser(String username) {
		User user = this.findByUsername(username);
		List<Role> roles = this.findRoles(user);
		List<Permission> permissions = this.findPermissions(roles);

		Set<String> rolesString = new HashSet<>();
		for(Role role: roles){
			rolesString.add(role.getRole());
		}
		Set<String> permissionsString = new HashSet<>();
		for(Permission permission: permissions){
			permissionsString.add(permission.getPermission());
		}
		
		UserExtend userExtend = new UserExtend();
		userExtend.setUser(user);
		userExtend.setRoles(roles);
		userExtend.setRolesString(rolesString);
		userExtend.setPermissions(permissions);
		userExtend.setPermissionsString(permissionsString);
		
		return userExtend;
	}
	
	private User findByUsername(String username) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(username);
		
		List<User> users = userMapper.selectByExample(example);
		
		if (users.size() == 0) {
			return null;
		}else {
			return users.get(0);
		}
	}
	
	private List<Role> findRoles(User user) {
		UserToRoleExample example = new UserToRoleExample();
		example.createCriteria().andUserIdEqualTo(user.getId());
		
		List<UserToRoleKey> userToRoleKeys = userToRoleMapper.selectByExample(example);
		List<Role> roles = new ArrayList<>();
		for(UserToRoleKey key: userToRoleKeys){
			Role role = roleMapper.selectByPrimaryKey(key.getRoleId());
			roles.add(role);
		}
		
		return roles;
	}
	
	private  List<Permission> findPermissions(List<Role> roles) {
		List<Long> roleIds = new ArrayList<>();
		for(Role role: roles){
			roleIds.add(role.getId());
		}
		
		List<Permission> permissions = new ArrayList<>();
		if (roles.size() == 0) {
			return permissions;
		}
		RoleToPermissionExample example = new RoleToPermissionExample();
		example.createCriteria().andRoleIdIn(roleIds);
		
		List<RoleToPermissionKey> roleToPermissionKeys = roleToPermissionMapper.selectByExample(example);
		
		for(RoleToPermissionKey key: roleToPermissionKeys){
			Permission permission = permissionMapper.selectByPrimaryKey(key.getPermissionId());
			permissions.add(permission);
		}
		
		return permissions;
	}

	@Override
	public void createUser(String username, String password, String roleName) throws UserExistException {
		User user = this.findByUsername(username);
		if (user != null) {
			throw new UserExistException();
		}
		
		user = new User();
		user.setId(null);
		user.setUsername(username);
		user.setPassword(password);
		
		PasswordHelper.encryptPassword(user);
		
		userMapper.insert(user);
		
		Role role = this.findRoleByRoleName(roleName);
		
		UserToRoleKey key = new UserToRoleKey();
		
		user = this.findByUsername(username);
		key.setUserId(user.getId());
		key.setRoleId(role.getId());
		
		userToRoleMapper.insert(key);
	}

	@Override
	public void changePassword(String username, String newPassword) {
		User user = this.findByUsername(username);
		user.setPassword(newPassword);
		PasswordHelper.encryptPassword(user);
		
		userMapper.updateByPrimaryKeySelective(user);
	}
	
	private Role findRoleByRoleName(String roleName){
		RoleExample example = new RoleExample();
		example.createCriteria().andRoleEqualTo(roleName);
		Role role = roleMapper.selectByExample(example).get(0);
		return role;
	}
}
