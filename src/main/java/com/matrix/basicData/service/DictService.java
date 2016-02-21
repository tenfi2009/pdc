package com.matrix.basicData.service;

import java.util.List;

import com.matrix.basicData.model.Dict;
import com.matrix.core.service.TreeService;

/**
 * @author Administrator
 */
public interface DictService extends TreeService<Dict, Long>{
	public List<Dict> getChildren(String parentId);
}
