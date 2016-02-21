package com.matrix.loan.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.Sort;
import com.matrix.core.common.enums.Status;
import com.matrix.core.util.Page;
import com.matrix.core.web.BaseController;
import com.matrix.core.web.util.AjaxResult;
import com.matrix.loan.model.LoanProduct;
import com.matrix.loan.service.ProductService;

@Controller               
@RequestMapping(value = "/loan/product")
public class ProductController extends BaseController{ 
	@Autowired
	private ProductService service;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		super.initBinder(binder);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list() {
		return "loan/product/list";
	}

	@RequestMapping(value="/listData", method = RequestMethod.GET)
	@ResponseBody
	public Object listData(@RequestParam(value = "page", defaultValue = "1") Integer currPage,
			@RequestParam(value = "rows", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "sidx") String sortField,
			@RequestParam(value = "sord") String sortType,
			String filters) {
		
		Page<LoanProduct> page = new Page<LoanProduct>();
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
	public @ModelAttribute("product") LoanProduct addNew(String parentId) {
		LoanProduct product = new LoanProduct();
		return product;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(String id) {
		return "loan/product/edit";
	}
	
	@ModelAttribute("product")
	public LoanProduct getValue(String id){
		LoanProduct product = null;
		if(StringUtils.isNotEmpty(id)){
			product = service.get(id);
		}
		if(null == product){
			product = new LoanProduct();
		}
		return product;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult save(@ModelAttribute("product")LoanProduct product) {
		AjaxResult rs = new AjaxResult();
		try{
			service.save(product);
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/config/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult config(@PathVariable String id) {
		AjaxResult rs = new AjaxResult();
		try{
			Map<String,Boolean> propertys = new HashMap<String, Boolean>();
			propertys.put("config", true);
			service.getDao().updateById(LoanProduct.class, id, propertys);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("配置成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("配置失败！<br/>" + e.getMessage());
		}
		return rs;
	}
	
	@RequestMapping(value="/publish/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult publish(@PathVariable String id) {
		AjaxResult rs = new AjaxResult();
		if(StringUtils.isEmpty(id)){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("提交时参数id不能为空！");
			return rs;
		}
		try{
			service.updateStatus(id, Status.PUBLISH);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("提交成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("提交失败！<br/>" + e.getMessage());
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
	
	@RequestMapping(value="/setInvalid/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult setInvalid(@PathVariable String id) {
		AjaxResult rs = new AjaxResult();
		if(StringUtils.isEmpty(id)){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("提交时参数id不能为空！");
			return rs;
		}
		try{
			service.updateStatus(id, Status.CANCEL);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("提交成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("提交失败！<br/>" + e.getMessage());
		}
		return rs;
	}
}
