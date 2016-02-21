package com.matrix.sys.service;

import java.util.Map;

import com.matrix.core.service.BasicDataService;
import com.matrix.sys.model.Resource;
import com.matrix.sys.model.User;
public interface UserService extends BasicDataService<User,String>{
	
	public User findUserByAccount(String account);
	
	public void updatePassword(String userId, String password);
	
	public Map<String,Resource> getRightResourceByUser(User user);
	
	/**
	 * 验证用户的当前密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean verifyCurrentPassword(String userId,String password);
}
