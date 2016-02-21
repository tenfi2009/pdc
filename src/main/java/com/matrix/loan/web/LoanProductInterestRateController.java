package com.matrix.loan.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.matrix.core.web.BaseController;
import com.matrix.loan.model.LoanProductInterestRate;
import com.matrix.loan.service.LoanProductInterestRateService;

@Controller               
@RequestMapping(value = "/loan/productInterestRate")
public class LoanProductInterestRateController extends BaseController{
	
	@Autowired
	private LoanProductInterestRateService service;
	
	@ModelAttribute("rate")
	public LoanProductInterestRate getValue(String id){
		LoanProductInterestRate rate = null;
		if(StringUtils.isNotEmpty(id)){
			rate = service.get(id);
		}
		if(null == rate){
			rate = new LoanProductInterestRate();
		}
		return rate;
	}

	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list() {
		return "loan/rate/list";
	}
	
	@RequestMapping(value="/loadRatePage", method = RequestMethod.GET)
	public String loadRatePage() {
		return "loan/rate/rate";
	}
}
