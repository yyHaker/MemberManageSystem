package cn.thinking.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.thinking.common.util.Const;

/**
 * 
 * Description: 在web.xml文件中配置监听器ContextLoaderListener,初始化WebApplicationContext
 * ApplicationContext是Spring的核心,context是上下文环境,ServletContextListener能够监听 ServletContext对象的生命周期
 * 
 * Department: 沈阳开发二部 
 * @author liu.jia_neu
 * @update [修改人] [修改时间]
 * @version 1.0
 *
 */
public class WebAppContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        Const.WEB_APP_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        //System.out.println("========获取Spring WebApplicationContext");
    }

    public void contextDestroyed(ServletContextEvent event) {
        // TODO Auto-generated method stub
    }
}
