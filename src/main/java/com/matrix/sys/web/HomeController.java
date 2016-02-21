package com.matrix.sys.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.matrix.core.web.BaseController;
import com.matrix.sys.model.OnlineUser;
import com.matrix.sys.service.ResourceService;

@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController{
	@Autowired
	private ResourceService resourceService;

	
	@RequestMapping( method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		OnlineUser onlineUser = getCurrentUser();
		String sysNavAccordion = resourceService.getSysNavAccordion(onlineUser.getId());
		
		model.addAttribute("sysNavAccordion",sysNavAccordion);
		model.addAttribute("userId",onlineUser.getId());
		
		return "home/index";
	}
}
