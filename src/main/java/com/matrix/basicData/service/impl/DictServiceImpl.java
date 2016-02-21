package com.matrix.basicData.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.matrix.basicData.dao.DictDao;
import com.matrix.basicData.model.Dict;
import com.matrix.basicData.service.DictService;
import com.matrix.core.dao.BaseDao;
import com.matrix.core.service.impl.TreeServiceImpl;

/**
 * @author Administrator
 */
@Service
public class DictServiceImpl extends TreeServiceImpl<Dict, Long> implements DictService{
	@Autowired
	private DictDao dao;
	
	@Override
	public DictDao getDao() {
		return dao;
	}

	
}
