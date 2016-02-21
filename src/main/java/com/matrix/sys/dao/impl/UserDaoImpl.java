package com.matrix.sys.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.matrix.core.dao.impl.BaseDaoImpl;
import com.matrix.sys.dao.UserDao;
import com.matrix.sys.enums.ResourceType;
import com.matrix.sys.model.Resource;
import com.matrix.sys.model.Role;
import com.matrix.sys.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao {

	@Override
	public User findUserByAccount(String account){
		Search search = new Search(User.class);
		search.addFilterEqual("account", account);
		return this.searchUnique(search);
	}

	@Override
	public List<Role> findRoles(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Search getFindPageSearch(Map queryParams) {
		Search search = super.getFindPageSearch(queryParams);
		search.addFilterNotEmpty("org.id");
		return search;
	}
	
	public Map<String,Resource> findRightResourceByUser(User user){
		Map<String,Resource> map = new HashMap<String,Resource>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT R.id,R.parent_id,R.name,R.uri,R.type,R.sort_no,R.description,R.icon ");
		sql.append("FROM t_sys_resource R,t_sys_role_resource RR, t_sys_user_role UR,t_sys_role RO ");
		sql.append("WHERE R.id = RR.resource_id AND RR.role_id=UR.role_id AND UR.role_id=RO.id ");
		sql.append("AND RO.status = ? AND UR.user_id = ? order by R.sort_no asc");
		SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
		
		sqlQuery.setParameter(0, com.matrix.core.common.enums.Status.VALID.ordinal());
		sqlQuery.setParameter(1, user.getId());
		List<Object[]> list = sqlQuery.list();
		
		if(null != list && list.size() > 0){
			Resource res = null;
			Resource parent = null;
			for(Object[] obj : list){
				res = new Resource();
				res.setId((String)obj[0]);
				
				//设置父节点
				if(StringUtils.isNotEmpty((String)obj[1])){
					parent = new Resource();
					parent.setId((String)obj[1]);
					res.setParent(parent);
				}else{
					res.setParent(null);
				}
				
				
				res.setName((String)obj[2]);
				res.setUri((String)obj[3]);
				res.setType(ResourceType.valueOf((Integer)obj[4]));
				res.setSortNo((Integer)obj[5]);
				res.setDescription((String)obj[6]);
				res.setIcon((String)obj[7]);
				map.put(res.getId(), res);
			}
		}
		
		return map;
	}
}
