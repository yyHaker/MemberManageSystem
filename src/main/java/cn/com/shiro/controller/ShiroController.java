package cn.com.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.core.store.response.MessageResponse;

@Controller
public class ShiroController {
	
	@RequestMapping("loginInner")
	@ResponseBody
	public MessageResponse login(HttpServletRequest request){
        String exceptionClassName = (String)request.getAttribute("failureKeyAttribute");
        String error = null;

        MessageResponse response = new MessageResponse();
        if (exceptionClassName.equals("success")) {
			response.setCode(1);
			Subject subject = SecurityUtils.getSubject();
			String url = "";
			if (subject.hasRole("root")) {
				url = "/root/index.html";
			}else if (subject.hasRole("manager")) {
				url = "/manager/index.html";
			}else {
				url = "/member/index.html";
			}
			

	        response.setMessage(url);
	        return response;
		}else {
			response.setCode(0);
	        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
	            error = "用户名/密码错误";
	        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
	            error = "用户名/密码错误";
	        } else if(exceptionClassName != null) {
	            error = "其他错误：" + exceptionClassName;
	        }
	        
	        response.setMessage(error);
	        return response;
		}
	}
}
