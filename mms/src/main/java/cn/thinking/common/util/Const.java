package cn.thinking.common.util;

import org.springframework.context.ApplicationContext;

/**
 * 
 * Description: 常量类,这里定义了session、权限、上下文环境等常量以供其他类和jsp复用，如：在登录的Controller写入Attrbute...
 * @update [修改人] [修改时间]
 * @version 1.0
 */
public class Const {
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_USER_RIGHTS = "sessionUserRights";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)).*";	//不对匹配该值的访问路径拦截（正则）
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	public static final int MENU_LEVEL = 4;
	
	// 登录人员的类型--------------------------------------------------------Start------------//    
    /**
     *	<p>系统管理员</p>
     */
    public final static int PERSON_TYPE_ADMIN = 1;

    /**
     * <p>其他</p>
     */
    public final static int PERSON_TYPE_OTHERS = 9;

    // 登录人员的类型---------------------------------------------------------End------------//
}
