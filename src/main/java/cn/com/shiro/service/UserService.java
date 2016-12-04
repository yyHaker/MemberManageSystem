package cn.com.shiro.service;

import cn.com.shiro.entity.extend.UserExtend;
import cn.com.shiro.exception.UserExistException;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserService {

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(String username, String newPassword);

//    /**
//     * 添加用户-角色关系
//     * @param userId
//     * @param roleIds
//     */
//    public void correlationRoles(Long userId, Long... roleIds);


//    /**
//     * 移除用户-角色关系
//     * @param userId
//     * @param roleIds
//     */
//    public void uncorrelationRoles(Long userId, Long... roleIds);
//
//    /**
//     * 根据用户名查找用户
//     * @param username
//     * @return
//     */
//    public User findByUsername(String username);
//
//    /**
//     * 根据用户名查找其角色
//     * @param username
//     * @return
//     */
//    public Set<String> findRoles(User user);
//
//    /**
//     * 根据用户名查找其权限
//     * @param username
//     * @return
//     */
//    public Set<String> findPermissions(String username);
	
	public UserExtend getUser(String username);

	void createUser(String username, String password, String role) throws UserExistException;

}
