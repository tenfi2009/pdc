package com.matrix.sys.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 * @author rong yang
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "login/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fail(HttpServletRequest request,@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		String error = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		return "login/login";
	}
}
