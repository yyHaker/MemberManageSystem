package cn.com.shiro.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.shiro.mapper.PermissionMapper;
import cn.com.shiro.service.PermissionService;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
    private PermissionMapper permissionMapper;

}
