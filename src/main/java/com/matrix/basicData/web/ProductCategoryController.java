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
import com.matrix.basicData.model.ProductCategory;
import com.matrix.basicData.service.ProCategoryService;
import com.matrix.core.util.Page;
import com.matrix.core.util.TreeUtils;
import com.matrix.core.web.util.AjaxResult;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/basicData/productCategory/")
public class ProductCategoryController {
	private static final Logger log = LoggerFactory.getLogger(ProductCategoryController.class);
	
	@Autowired
	private ProCategoryService service;
	
	@ModelAttribute("pro")
	public ProductCategory getValue(Long id){
		ProductCategory pro = null;
		if(id != null){
			pro = service.get(id);
		}else{
			pro = new ProductCategory();
		}
		return pro;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list() {
		return "basicData/productCategory/list";
	}
	
	@RequestMapping(value="/listData", method = RequestMethod.GET)
	@ResponseBody
	public Object listData(@RequestParam Long parentId, 
			@RequestParam(value = "page", defaultValue = "1") Integer currPage,
			@RequestParam(value = "rows", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "sidx") String sortField,
			@RequestParam(value = "sord") String sortType,
			String filters) {
		
		Page<ProductCategory> page = new Page<ProductCategory>();
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
	public @ModelAttribute("pro") ProductCategory addNew(Long parentId) {
		ProductCategory pro = new ProductCategory();
		if(parentId != null){
			ProductCategory parent = this.service.get(parentId);
			pro.setParent(parent);
		}
		return pro;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(String id) {
		return "basicData/productCategory/edit";
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
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult save(@ModelAttribute("pro")ProductCategory pro) {
		AjaxResult rs = new AjaxResult();
		try{
			if(null != pro.getParent() && null == pro.getParent().getId()){
				pro.setParent(null);
			}
			pro.setType(1);//默认为1。1：贷款产品分类 ，2： 理财产品分类
			service.save(pro);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("保存成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("保存失败！<br/>" + e.getMessage());
		}
		return rs;
	}
	
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
	 * 
	 * @param id 组织ID
	 * @return
	 */
	@RequestMapping(value="/getProTree", method = RequestMethod.POST)
	@ResponseBody
	public String  getProTree(String id) {
		return TreeUtils.treeToJson(service.getChildren(id));
	}
}
