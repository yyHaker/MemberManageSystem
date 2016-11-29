package cn.com.shiro.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.shiro.mapper.RoleMapper;
import cn.com.shiro.service.RoleService;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
    private RoleMapper roleMapper;

}
