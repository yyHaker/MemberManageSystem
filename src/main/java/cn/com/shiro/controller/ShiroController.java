package cn.com.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.core.manager.entity.Manager;
import cn.com.core.manager.request.ManagerJson;
import cn.com.core.manager.service.ManagerService;
import cn.com.core.manager.utils.BeanUtils;
import cn.com.core.member.entity.Member;
import cn.com.core.member.request.MemberJson;
import cn.com.core.member.service.impl.MemberService;
import cn.com.core.root.Root;
import cn.com.core.store.response.MessageResponse;
import cn.com.shiro.exception.UserNotExistException;
import cn.com.shiro.request.PasswordRequest;
import cn.com.shiro.response.UserResponse;
import cn.com.shiro.service.UserService;

@Controller
public class ShiroController {
	@Autowired
	private UserService userService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private MemberService memberService;

	@RequestMapping("loginInner")
	@ResponseBody
	public MessageResponse login(HttpServletRequest request) {
		String exceptionClassName = (String) request.getAttribute("failureKeyAttribute");
		String error = null;

		MessageResponse response = new MessageResponse();
		if (exceptionClassName.equals("success")) {
			response.setCode(1);
			Subject subject = SecurityUtils.getSubject();
			String url = "";
			if (subject.hasRole("root")) {
				url = "/root/index.html";
			} else if (subject.hasRole("manager")) {
				url = "/manager/index.html";
			} else {
				url = "/member/index.html";
			}

			response.setMessage(url);
			return response;
		} else {
			response.setCode(0);
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				error = "用户名/密码错误";
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				error = "用户名/密码错误";
			} else if (exceptionClassName != null) {
				error = "其他错误：" + exceptionClassName;
			}

			response.setMessage(error);
			return response;
		}
	}

	@ResponseBody
	@RequestMapping("/changepassword")
	public MessageResponse changepassword(@RequestBody PasswordRequest request) {
		MessageResponse response = new MessageResponse();
		try {
			userService.changePassword(request.getUsername(), request.getPassword());
			response.setCode(1);
		} catch (Exception e) {
			response.setCode(0);
		}
		return response;
	}

	@ResponseBody
	@RequestMapping("/getUser")
	public UserResponse getUser() {
		UserResponse response = new UserResponse();
		Subject subject = SecurityUtils.getSubject();

		if (subject.hasRole("root")) {
			response.setRole("root");
			
			Root root = new Root();
			root.setUsername((String) subject.getPrincipal());
			
			response.setRoot(root);
		} else if (subject.hasRole("manager")) {
			response.setRole("manager");
			
			Manager manager = managerService.getManagerById((String) subject.getPrincipal());
			ManagerJson managerJson = BeanUtils.toManagerJson(manager);
			
			response.setManagerJson(managerJson);
		} else if (subject.hasRole("member")) {
			response.setRole("member");
			
			Member member = memberService.getMemberById((String) subject.getPrincipal());
			MemberJson memberJson = cn.com.core.member.utils.BeanUtils.toMemberJson(member);
			
			response.setMemberJson(memberJson);
		}else {
			response.setRole("error");
		}
		return response;
	}
}
