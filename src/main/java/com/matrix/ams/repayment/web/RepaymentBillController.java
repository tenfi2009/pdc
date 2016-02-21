package com.matrix.ams.repayment.web;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.Sort;
import com.matrix.ams.loan.model.Bill;
import com.matrix.ams.loan.model.LoanRepaymentOperationLog;
import com.matrix.ams.loan.service.BillService;
import com.matrix.ams.loan.service.LoanBillService;
import com.matrix.ams.loan.service.LoanRepaymentOperationLogService;
import com.matrix.ams.repayment.model.RepaymentBill;
import com.matrix.ams.repayment.model.SettleBillDTO;
import com.matrix.ams.repayment.service.RepaymentBillService;
import com.matrix.core.util.Page;
import com.matrix.core.web.BaseController;
import com.matrix.core.web.util.AjaxResult;

@Controller
@RequestMapping(value="/repayment/bill")
public class RepaymentBillController extends BaseController{
	
	@Autowired
	private RepaymentBillService repaymentBillService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private LoanBillService service;
	
	@Autowired 
	private LoanRepaymentOperationLogService logService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		super.initBinder(binder);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		dateFormat.setLenient(false);
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list() {
		return "ams/repayment/list";
	}
	
	@RequestMapping(value="/listData", method = RequestMethod.GET)
	@ResponseBody
	public Object listData(@RequestParam(value = "page", defaultValue = "1") Integer currPage,
			@RequestParam(value = "rows", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "sidx") String sortField,
			@RequestParam(value = "sord") String sortType,
			String filters) {
		
		Page<SettleBillDTO> page = new Page<SettleBillDTO>();
		page.setPageSize(pageSize);
		page.setCurrPage(currPage);
		if(StringUtils.isNotEmpty(sortField)){
			page.getSorts().add(new Sort(sortField, "desc".equalsIgnoreCase(sortType) ? true : false));
		}
		
		Map queryParams = new HashMap();
		if(StringUtils.isNotEmpty(filters)){
			queryParams.put("queryConditions", filters);
		}
		
		page = repaymentBillService.findSettleBillPage(page, queryParams);
		return page;
	}
	
	@RequestMapping(value="/addNew", method = RequestMethod.GET)
	public String addNew() {
		return "ams/loan/addNew";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult save(HttpServletRequest req, Bill bill, RepaymentBill repaymentBill) {
		AjaxResult rs = new AjaxResult();
		try{
			Long billId = Long.valueOf(req.getParameter("bill.id"));
			String repaymentBillId = req.getParameter("repaymentBill.id");
			bill.setId(billId);
			repaymentBill.setId(repaymentBillId);
			repaymentBillService.saveRepaymentSettleBill(bill,repaymentBill);
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("保存失败！<br/>" + e.getMessage());
		}
		return rs;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(Integer id, ModelMap map) {
		SettleBillDTO billDto = repaymentBillService.findSettleBillDTOByBillId(id);
		Bill bill = billService.get(Long.valueOf(id));
		RepaymentBill repaymentBill = repaymentBillService.findRepaymentBillByBillId(id.intValue());
		map.put("billDto", billDto);
		map.put("bill", bill);
		map.put("repaymentBill", repaymentBill);
		
		return "ams/repayment/edit";
	}
	
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public String view(Long id,ModelMap map) {
		Bill bill = billService.get(id);
		SettleBillDTO settleBillDTO = repaymentBillService.findSettleBillDTOByBillId(id.intValue());
		RepaymentBill repaymentBill = repaymentBillService.findRepaymentBillByBillId(id.intValue());
		List<LoanRepaymentOperationLog> logs = logService.findLogByObjectId(String.valueOf(id));
		map.put("bill", bill);
		map.put("settleBillDTO", settleBillDTO);
		map.put("repaymentBill", repaymentBill);
		map.put("logs", logs);
		return "ams/repayment/view";
	}
	
	/**
	 * 
	 * @param valueDate 账单应还日期
	 * @param payDate  打款日期
	 * @param penalty  罚息
	 * @param capitalFee 本金+费用
	 * @param billAmount 账单应付
	 * @return
	 */
	@RequestMapping(value="/calculatePenalty", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult calculatePenaltyInterest (String valueDate,String payDate,Double penalty,Double capitalFee,Double billAmount) {
		AjaxResult rs = new AjaxResult();
		Map data = new HashMap();
		try{
			String format = "yyyy-MM-dd";
			DateTime dtValueDate =  DateTimeFormat.forPattern(format).parseDateTime(valueDate);
			DateTime dtPayDate =  DateTimeFormat.forPattern(format).parseDateTime(payDate);
			
			if(dtPayDate.isAfter(dtValueDate)){
				int days = Days.daysBetween(dtValueDate, dtPayDate).getDays();
				BigDecimal newPenalty = BigDecimal.valueOf(capitalFee).multiply(new BigDecimal("0.01")).multiply(new BigDecimal(days)).setScale(2, RoundingMode.HALF_DOWN);
				
				BigDecimal dtPenalty = BigDecimal.valueOf(penalty).subtract(newPenalty).setScale(2, RoundingMode.HALF_DOWN);
				BigDecimal newBillAmount = BigDecimal.valueOf(billAmount).subtract(dtPenalty).setScale(2, RoundingMode.HALF_DOWN);
				data.put("dtPenalty", dtPenalty);
				data.put("billAmount", newBillAmount);
			}else{
				data.put("dtPenalty", penalty);
				data.put("billAmount", billAmount - penalty);
			}
			
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setData(data);
			rs.setMsg("计算成功！");
		}catch(Exception e){
			e.printStackTrace();
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("计算失败！<br/>" + e.getMessage());
		}
		return rs;
	}
}
