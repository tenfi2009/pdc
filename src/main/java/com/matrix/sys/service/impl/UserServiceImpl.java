package com.matrix.sys.service.impl;

import java.util.Map;

import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrix.core.common.enums.Status;
import com.matrix.core.service.impl.BasicDataServiceImpl;
import com.matrix.sys.dao.UserDao;
import com.matrix.sys.model.Resource;
import com.matrix.sys.model.User;
import com.matrix.sys.service.UserService;

@Service
public class UserServiceImpl extends BasicDataServiceImpl<User,String> implements UserService{
	@Autowired
	private UserDao dao;
	
	@Autowired
	PasswordService passwordService;
	
	public UserDao getDao(){
		return dao;
	}
	
	

	@Override
	public User findUserByAccount(String account){
		return dao.findUserByAccount(account);
	}



	@Override
	public void updateStatus(String id, Status status) {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected void setAddNew(User user) {
		super.setAddNew(user);
		//默认启用
		user.setIsEnable(true);
		//password加密
		String encryptedValue = passwordService.encryptPassword(user.getPassword());
		user.setPassword(encryptedValue);
	}
	
	public void updatePassword(String userId, String password) {
		User user = get(userId);
		//password加密
		String encryptedValue = passwordService.encryptPassword(password);
		user.setPassword(encryptedValue);
		save(user);
	}
	
	public Map<String,Resource> getRightResourceByUser(User user){
		return dao.findRightResourceByUser(user);
	}
	
	/**
	 * 验证用户的当前密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean verifyCurrentPassword(String userId,String password){
		User user = dao.find(userId);
		return passwordService.passwordsMatch(password, user.getPassword());
	}
}
