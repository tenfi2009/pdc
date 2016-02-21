package com.matrix.sys.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.genericdao.search.Sort;
import com.matrix.core.util.Page;
import com.matrix.core.web.BaseController;
import com.matrix.core.web.util.AjaxResult;
import com.matrix.sys.model.Role;
import com.matrix.sys.model.User;
import com.matrix.sys.service.RoleService;
import com.matrix.sys.service.UserRoleService;
import com.matrix.sys.service.UserService;

@Controller
@RequestMapping(value = "/sys/user")
public class UserController extends BaseController{ 
	@Autowired
	private UserService service;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserRoleService userRoleService;

	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	@RequiresRoles("2administrator")
	public String list() {
		return "sys/user/list";
		
	}

	@RequestMapping(value="/listData", method = RequestMethod.GET)
	@ResponseBody
	public Object listData(@RequestParam(value = "page", defaultValue = "1") Integer currPage,
			@RequestParam(value = "rows", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "sidx") String sortField,
			@RequestParam(value = "sord") String sortType,
			String filters) {
		
		Page<User> page = new Page<User>();
		page.setPageSize(pageSize);
		page.setCurrPage(currPage);
		if(StringUtils.isNotEmpty(sortField)){
			page.getSorts().add(new Sort(sortField, "desc".equalsIgnoreCase(sortType) ? true : false));
		}
		
		Map queryParams = new HashMap();
		if(StringUtils.isNotEmpty(filters)){
			queryParams.put("filters", filters);
		}
		
		page = service.findPage(page, queryParams);
		return page;
	}
	
	@RequestMapping(value="/addNew", method = RequestMethod.GET)
	public @ModelAttribute("user") User addNew(String parentId) {
		User user = new User();
		return user;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(String id) {
		return "sys/user/edit";
	}
	
	@RequestMapping(value="/resetPw", method = RequestMethod.GET)
	public String resetPw(String id) {
		return "sys/user/resetPw";
	}
	
	@RequestMapping(value="/editPwd", method = RequestMethod.GET)
	public String editPwd(String id) {
		return "sys/user/editPwd";
	}
	
	@RequestMapping(value="/saveEditPwd", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveEditPwd(String userId,String oldPwd,String newpwd,String repwd) {
		AjaxResult rs = new AjaxResult();
		try{
			StringBuffer error = new StringBuffer();
			if(StringUtils.isEmpty(oldPwd)){
				error.append("当前密码不能为空！").append("<br/>");
			}
			if(StringUtils.isEmpty(newpwd)){
				error.append("新密码不能为空！").append("<br/>");
			}
			if(StringUtils.isEmpty(repwd)){
				error.append("新密码确认不能为空！").append("<br/>");
			}
			
			if(error.length() > 0){
				rs.setStatus(AjaxResult.STATUS_ERROR);
				rs.setMsg(error.toString());
				return rs;
			}
			
			boolean isValid = service.verifyCurrentPassword(userId, oldPwd);
			if(!isValid){
				rs.setStatus(AjaxResult.STATUS_ERROR);
				rs.setMsg("当前密码不正确！");
				return rs;
			}
			
			if(!newpwd.equals(repwd)){
				rs.setStatus(AjaxResult.STATUS_ERROR);
				rs.setMsg("新密码与新密码确认不一致！");
				return rs;
			}
			
			service.updatePassword(userId, newpwd);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("修改密码成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("修改密码失败！<br/>" + e.getMessage());
		}
		return rs;
	}
	
	@RequestMapping(value="/saveNewPw", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveNewPw(String userId,String newPassword) {
		AjaxResult rs = new AjaxResult();
		try{
			service.updatePassword(userId, newPassword);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("重置密码成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("重置密码失败！<br/>" + e.getMessage());
		}
		return rs;
	}
	
	@RequestMapping(value="/assignRole", method = RequestMethod.GET)
	public ModelAndView assignRole(String id) {
		ModelAndView mav = new ModelAndView("sys/user/assignRole");
		Collection<Role> roles = roleService.getAll();
		
		List<Role> assignedRoles = userRoleService.getRoleByUserId(id);
		Set<String> assignedRoleIds = new HashSet<String>();
		for(Role role : assignedRoles){
			assignedRoleIds.add(role.getId());
		}
		
		for(Role role : roles){
			role.getAdditional().put("isAssigned", assignedRoleIds.contains(role.getId()));
		}
		
		
		mav.addObject("roles", roles);
		mav.addObject("assignedRoleIds", assignedRoleIds);
		mav.addObject("userId", id);
		return mav;
	}
	
	
	
	@ModelAttribute("user")
	public User getValue(String id){
		User user = null;
		if(StringUtils.isNotEmpty(id)){
			user = service.get(id);
		}
		if(null == user){
			user = new User();
		}
		return user;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult save(@ModelAttribute("user")User user) {
		AjaxResult rs = new AjaxResult();
		try{
			service.save(user);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("保存成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("保存失败！<br/>" + e.getMessage());
		}
		return rs;
	}
	
	@RequestMapping(value="/remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult remove(@PathVariable String id) {
		AjaxResult rs = new AjaxResult();
		if(StringUtils.isEmpty(id)){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("删除时参数id不能为空！");
			return rs;
		}
		try{
			service.removeById(id);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("删除成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("删除失败！<br/>" + e.getMessage());
		}
		
		return rs;
	}

	@RequestMapping(value="/submit/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult submit(@PathVariable String id) {
		AjaxResult rs = new AjaxResult();
		if(StringUtils.isEmpty(id)){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("提交时参数id不能为空！");
			return rs;
		}
		try{
			service.submit(id);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("提交成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("提交失败！<br/>" + e.getMessage());
		}
		
		return rs;
	}
}
