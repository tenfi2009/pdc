package com.matrix.basicData.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.matrix.basicData.dao.ProCategoryDao;
import com.matrix.basicData.model.ProductCategory;
import com.matrix.core.dao.impl.BaseDaoImpl;
import com.matrix.core.util.SQLUtil;

/**
 * @author Administrator
 */
@Repository
public class ProCategoryDaoImpl extends BaseDaoImpl<ProductCategory, Long> implements ProCategoryDao{
	@Override
	public Search getFindPageSearch(Map params) {
		Search search = super.getFindPageSearch(params);
		StringBuffer filter  = new StringBuffer(" 1=1 ");
		
		if (StringUtils.isNotEmpty((String)params.get("id"))){
			filter.append(" and {id} = '").append((String)params.get("id")).append("'");
		}
		
		if(params.containsKey("parentId") && StringUtils.isNotEmpty((String)params.get("parentId"))){
			filter.append(" and {parent.id} = ").append(params.get("parentId"));
		}else{
			filter.append(" and {parent.id} = null");
		}
		
		
		if (StringUtils.isNotEmpty((String)params.get("levelCode"))){
			filter.append(" and {levelCode} like '").append((String)params.get("levelCode")).append("%'");
		}
		
		if (StringUtils.isNotEmpty((String)params.get("status"))){
			filter.append(" and {status} = '").append((String)params.get("status")).append("'");
		}
		
		if (StringUtils.isNotEmpty((String)params.get("code"))){
			filter.append(" and {code} like '%").append(SQLUtil.escapeSQLLike((String)params.get("code"))).append("%' escape'/'");
		}
		
		if (StringUtils.isNotEmpty((String)params.get("name"))){
			filter.append(" and {name} like '%").append(SQLUtil.escapeSQLLike((String)params.get("name"))).append("%' escape'/'");
		}
		
		
		search.addFilterCustom(filter.toString());
		
		if (null != params.get("exclude")){
			//filter.append(" and {id} not in (").append((List)params.get("exclude")).append(")");
			search.addFilterNotIn("id", (List)params.get("exclude"));
		}
		
		if (null != params.get("include")){
			search.addFilterIn("id", (List)params.get("include"));
		}
		
		
		search.addSortAsc("sortNo");
		
		
		return search;
	}
}
