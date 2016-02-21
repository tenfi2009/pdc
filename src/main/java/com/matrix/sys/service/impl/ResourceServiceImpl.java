/**
 * <b>包名：</b>com.matrix.sys.service.impl<br/>
 * <b>文件名：</b>ResourceServiceImpl.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-26-下午10:36:21<br/>
 * <br/>
 */
package com.matrix.sys.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.search.Search;
import com.matrix.core.common.enums.Status;
import com.matrix.core.dao.BaseDao;
import com.matrix.core.service.impl.TreeServiceImpl;
import com.matrix.sys.common.SysConstants;
import com.matrix.sys.dao.ResourceDao;
import com.matrix.sys.model.Resource;
import com.matrix.sys.model.User;
import com.matrix.sys.service.ResourceService;
import com.matrix.sys.service.UserService;

/**
 * <b>类名称：</b>ResourceServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-26 下午10:36:21<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 * 
 */
public class ResourceServiceImpl extends TreeServiceImpl<Resource, String> implements ResourceService {
	@Autowired
	private ResourceDao dao;

	@Autowired
	private UserService userService;

	@Override
	public BaseDao getDao() {
		return dao;
	}

	protected void setDefaultValue(Resource res) {
		super.setDefaultValue(res);
		res.setStatus(Status.VALID);
	}

	/**
	 * 获取指定的用户系统导航Accordion
	 * 
	 * @param userId
	 * @return
	 */
	public String getSysNavAccordion(String userId) {
		User user = userService.get(userId);
		// 是否超级管理员
		List<Resource> resList = null;
		if (SysConstants.WEBMASTER.equals(user.getAccount())) {
			resList = getSuperAdminResource();
		} else {
			// 用户有权限的资源
			Map<String, Resource> rightedResource = userService.getRightResourceByUser(user);
			if (null != rightedResource && rightedResource.size() > 0) {
				resList = new ArrayList<Resource>(rightedResource.size());
				Iterator<Resource> it = rightedResource.values().iterator();
				while (it.hasNext()) {
					resList.add(it.next());
				}

				Collections.sort(resList, new Comparator<Resource>() {
					public int compare(Resource o1, Resource o2) {
						int num1 = null == o1.getSortNo() ? 1000 : o1.getSortNo();
						int num2 = null == o2.getSortNo() ? 1000 : o2.getSortNo();
						return num1 - num2;
					}
				});
			}
		}

		StringBuffer accordion = new StringBuffer();
		if (null != resList && resList.size() > 0) {
			int size = resList.size();
			Resource group = null;
			Resource res = null;

			for (int i = 0; i < size; i++) {
				group = resList.get(i);
				if (null != group.getParent()) {
					continue;
				}

				// 是否有下级节点
				boolean hasChildren = false;
				for (int k = 0; k < size; k++) {
					res = resList.get(k);
					if (null != res.getParent() && res.getParent().getId().equals(group.getId())) {
						hasChildren = true;
						break;
					}
				}
				if (hasChildren) {
					accordion.append("<li>");
					accordion.append("<a href=\"#\" class=\"dropdown-toggle\">");
					accordion.append("<i class=\"").append(group.getIcon()).append("\"></i>");
					accordion.append("<span>").append(group.getName()).append("</span>");
					accordion.append("<b class=\"arrow icon-angle-down\"></b>");
					accordion.append("</a>");
					accordion.append("<ul class=\"submenu\">");
					for (int k = 0; k < size; k++) {
						res = resList.get(k);
						if (null != res.getParent() && res.getParent().getId().equals(group.getId())) {
							accordion.append("<li>");
							accordion.append("<a href=\"").append(res.getUri()).append("\" target=\"center\">");
							accordion.append("<i class=\"").append(res.getIcon()).append("\"></i>");
							accordion.append(res.getName());
							accordion.append("</a>");
							accordion.append("</li>");
						}
					}
					accordion.append("</ul>");
					accordion.append("</li>");
				} else {
					accordion.append("<li>");
					accordion.append("<a href=\"").append(group.getUri()).append("\" target=\"center\">");
					accordion.append("<i class=\"").append(group.getIcon()).append("\"></i>");
					accordion.append("<span>").append(group.getName()).append("</span>");
					accordion.append("</a>");
					accordion.append("</li>");
				}
			}

		}

		return accordion.toString();
	}

	private List<Resource> getSuperAdminResource() {
		Search search = new Search(Resource.class);
		search.addFilterEqual("isSuper", true);
		search.addSortAsc("sortNo");
		return dao.search(search);
	}
}
