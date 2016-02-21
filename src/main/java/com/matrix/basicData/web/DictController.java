package com.matrix.basicData.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.Sort;
import com.matrix.basicData.model.Dict;
import com.matrix.basicData.service.DictService;
import com.matrix.core.util.Page;
import com.matrix.core.util.TreeUtils;
import com.matrix.core.web.util.AjaxResult;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/basicData/dict")
public class DictController {
	
	private static final Logger log = LoggerFactory.getLogger(DictController.class);
	
	@Autowired
	private DictService service;
	
	@ModelAttribute("dict")
	public Dict getValue(Long id){
		Dict dict = null;
		if(id != null){
			dict = service.get(id);
		}else{
			dict = new Dict();
		}
		return dict;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list() {
		return "basicData/dict/list";
	}
	
	/**
	 * 加载列表
	 * @param parentId
	 * @param currPage
	 * @param pageSize
	 * @param sortField
	 * @param sortType
	 * @param filters
	 * @return
	 */
	@RequestMapping(value="/listData", method = RequestMethod.GET)
	@ResponseBody
	public Object listData(@RequestParam String parentId, 
			@RequestParam(value = "page", defaultValue = "1") Integer currPage,
			@RequestParam(value = "rows", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "sidx") String sortField,
			@RequestParam(value = "sord") String sortType,
			String filters) {
		Page<Dict> page = new Page<Dict>();
		page.setPageSize(pageSize);
		page.setCurrPage(currPage);
		if(StringUtils.isNotEmpty(sortField)){
			page.getSorts().add(new Sort(sortField, "desc".equalsIgnoreCase(sortType) ? true : false));
		}
		
		Map queryParams = new HashMap();
		queryParams.put("parentId", parentId);
		if(StringUtils.isNotEmpty(filters)){
			queryParams.put("filters", filters);
		}
		
		page = service.findPage(page, queryParams);
		return page;
	}
	
	@RequestMapping(value="/addNew", method = RequestMethod.GET)
	public @ModelAttribute("dict")Dict addNew(Long parentId) {
		Dict dict = new Dict();
		if(parentId != null){
			Dict parent = this.service.get(parentId);
			dict.setParent(parent);
		}
		return dict;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(Long id) {
		return "basicData/dict/edit";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult save(@ModelAttribute("dict")Dict dict) {
		AjaxResult rs = new AjaxResult();
		try{
			service.save(dict);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("保存成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("保存失败！<br/>" + e.getMessage());
			e.printStackTrace();
		}
		return rs;
	}
	
	@RequestMapping(value="/remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult remove(@PathVariable Long id) {
		AjaxResult rs = new AjaxResult();
		if(id == null){
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
	
	/**
	 * 提交数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/submit/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult submit(@PathVariable Long id) {
		AjaxResult rs = new AjaxResult();
		if(id == null){
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
	
	
	
	/**
	 * 加载树
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getDictTree", method = RequestMethod.POST)
	@ResponseBody
	public String  getDictTree(String id) {
		return TreeUtils.treeToJson(service.getChildren(id));
	}
}
