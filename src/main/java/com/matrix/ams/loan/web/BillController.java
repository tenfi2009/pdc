package com.matrix.ams.loan.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.matrix.ams.loan.model.Bill;
import com.matrix.ams.loan.service.BillService;
import com.matrix.core.web.BaseController;
import com.matrix.core.web.util.AjaxResult;

@Controller
@RequestMapping(value="/repayment/bill")
public class BillController extends BaseController{
	
	@Autowired
	private BillService service;
	
	@RequestMapping(value="/list/{orderId}", method = RequestMethod.GET)
	public String list(@PathVariable String orderId,ModelMap map) {
		List<Bill> bills = service.findBillByOrderId(orderId);
		map.put("bills", bills);
		return "ams/loan/repaymentPlan";
	}
	
	@RequestMapping(value="/saveExtraFee", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveExtraFee(HttpServletRequest request) {
		AjaxResult rs = new AjaxResult();
		try{
			List<Bill> bills = new ArrayList<Bill>();
			for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();){
				String element = e.nextElement();
				if (element.startsWith("extraFee_")) {
					Long billId = Long.valueOf(element.split("_")[1]);
					Bill bill = service.get(billId);
					String value = request.getParameter(element);
					try {
						if (StringUtils.isEmpty(value)) {
							bill.setExtraFee(Double.valueOf(0));
						} else {
							bill.setExtraFee(Double.valueOf(value));
						}
					} catch (Exception e1) {
						bill.setExtraFee(Double.valueOf(0));
					}
					bills.add(bill);
				}
			}
			service.save(bills);
			
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("保存失败！<br/>" + e.getMessage());
		}
		return rs;
	}
}
