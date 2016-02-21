package com.matrix.ams.loan.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.Sort;
import com.matrix.ams.loan.model.LoanBill;
import com.matrix.ams.loan.model.LoanRepaymentOperationLog;
import com.matrix.ams.loan.service.LoanBillService;
import com.matrix.ams.loan.service.LoanRepaymentOperationLogService;
import com.matrix.core.util.Page;
import com.matrix.core.web.BaseController;
import com.matrix.core.web.util.AjaxResult;

@Controller
@RequestMapping(value="/loan/bill")
public class LoanBillController extends BaseController{
	
	@Autowired
	private LoanBillService service;
	
	@Autowired 
	private LoanRepaymentOperationLogService logService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		super.initBinder(binder);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");  
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat1,
				true);
		binder.registerCustomEditor(Date.class, "valueDate", orderDateEditor);
	}
	
	@ModelAttribute("bill")
	public LoanBill getValue(String id){
		LoanBill bill = null;
		if(StringUtils.isNotEmpty(id)){
			bill = service.get(id);
		}
		if(null == bill){
			bill = new LoanBill();
			bill.setBizDate(new Date());
			bill.setOperator(getCurrentUser().getName());
			//bill.setBillNumber(service.generateBillNumber());
		}
		return bill;
	}

	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list() {
		return "ams/loan/list";
	}
	
	@RequestMapping(value="/failList", method = RequestMethod.GET)
	public String failList() {
		return "ams/loan/failList";
	}
	
	@RequestMapping(value="/listData", method = RequestMethod.GET)
	@ResponseBody
	public Object listData(@RequestParam(value = "page", defaultValue = "1") Integer currPage,
			@RequestParam(value = "rows", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "sidx") String sortField,
			@RequestParam(value = "sord") String sortType,
			String filters,@RequestParam(value = "opType") String opType) {
		
		Page<LoanBill> page = new Page<LoanBill>();
		page.setPageSize(pageSize);
		page.setCurrPage(currPage);
		if(StringUtils.isNotEmpty(sortField)){
			page.getSorts().add(new Sort(sortField, "desc".equalsIgnoreCase(sortType) ? true : false));
		}
		
		Map queryParams = new HashMap();
		if(StringUtils.isNotEmpty(filters)){
			queryParams.put("filters", filters);
		} else {
			if ("fail".equals(opType)) {//展示放款失败列表
				queryParams.put("filters", "{\"groupOp\":\"AND\",\"rules\":[{\"t\":\"i\",\"f\":\"status\",\"op\":\"eq\",\"v\":\"4\"}]}");
			} else {//展示正常放款列表
				queryParams.put("filters", "{\"groupOp\":\"AND\",\"rules\":[{\"t\":\"i\",\"f\":\"status\",\"op\":\"lt\",\"v\":\"4\"}]}");
			}
		}
		
		page = service.findPage(page, queryParams);
		return page;
	}
	
	@RequestMapping(value="/addNew", method = RequestMethod.GET)
	public String addNew() {
		return "ams/loan/addNew";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult save(@ModelAttribute("bill") LoanBill bill) {
		AjaxResult rs = new AjaxResult();
		try{
			service.saveLoanBill(bill);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("保存失败！<br/>" + e.getMessage());
		}
		return rs;
	}
	
	@RequestMapping(value="/failSave", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult failSave(@ModelAttribute("bill") LoanBill bill) {
		AjaxResult rs = new AjaxResult();
		try{
			service.saveFailLoanBill(bill);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("保存成功！");
		}catch(Exception e){
			e.printStackTrace();
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
			service.deleteLoanBill(id);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("删除成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("删除失败！<br/>" + e.getMessage());
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
			e.printStackTrace();
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("提交失败！<br/>" + e.getMessage());
		}
		
		return rs;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(String id) {
		return "ams/loan/edit";
	}
	
	@RequestMapping(value="/failEdit", method = RequestMethod.GET)
	public String failEdit(String id) {
		return "ams/loan/failEdit";
	}
	
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public String view(String id,ModelMap map) {
		List<LoanRepaymentOperationLog> logs = logService.findLogByObjectId(id);
		map.put("logs", logs);
		return "ams/loan/view";
	}
	
}
