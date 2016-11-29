package cn.thinking.common.resolver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.thinking.common.exception.BusinessException;
import cn.thinking.common.exception.ParameterException;

/**
 * 
 * Description:在spring配置文件applicationContext.xml中配置注释掉了,id="exceptionHandler"的bean制定了该类的返回模型
 * 			        当前为注释状态,暂使用web.xml的错误页面跳转,若有相对复杂需求,请打开注释并作相应处理,对应处理模型为common/exception中的类
 * 
 * Department: 沈阳开发二部 
 * @author liu.jia_neu
 * @update [修改人] [修改时间]
 * @version 1.0
 *
 */
public class MyExceptionResolver implements HandlerExceptionResolver{

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		
		// 根据不同错误转向不同页面
		if(ex instanceof BusinessException) {
			return new ModelAndView("/errors/error-business", model);
		}else if(ex instanceof ParameterException) {
			return new ModelAndView("/errors/error-parameter", model);
		} else {
			return new ModelAndView("/errors/error", model);
		}
	}

}
