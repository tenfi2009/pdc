package com.matrix.ams.loan.model;

import com.google.common.base.Strings;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 订单信息
 * 
 * @author hu.tang
 * 
 */
public class GoodsOrder implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 3514597010195779719L;

	private long id;

	/** 订单号 **/
	private String orderId;

	/** 用户id **/
	private int userId;

	/** 订单商品数量 **/
	private int skuNum;

	/** 商品价格，下单时的价格 **/
	private double skuPrice;

	/** 商品名称，下单时的名称 **/
	private String skuName;

	/** 商品图片，下单时 **/
	private String skuLogo = "";

	/** 地区id **/
	private int regionId;

	/** 属性信息 **/
	private String attrInfo = "";

	/* 订单类型常量 */
	/** 自营商品 **/
	public final static int TYPE_SELF_ORDER = 1;
	/** 第三方接入商品 **/
	public final static int TYPE_THIRD_ORDER = 2;
	/** 用户链接商品 **/
	public final static int TYPE_LINK_ORDER = 3;
	/**现金贷款商品**/
	public final static int TYPE_MONEY_ORDER = 4;
	/**预约订单**/
	public final static int TYPE_APPOINTMENT_ORDER = 5;

	/** 订单类型，自营订单，外链订单 **/
	private int type;

	/* 订单状态常量 */
	/** 用户正在编辑，没有用到。 **/
	public final static int STATUS_EDITING = 0;
	/** 用户在审核阶段提交的订单,此状态下只能提交一笔订单。订单可以取消。 **/
	public final static int STATUS_USER_AUDITING = 1;
	/** 审核通过用户提交的订单。订单可以取消。 **/
	public final static int STATUS_SUBMIT = 2;
	/** 订单审核中。有人操作。 **/
	public final static int STATUS_AUDITING = 3;
	/** 订单等待首付，对于有首付的订单。 **/
	public final static int STATUS_WAIT_FIRST_PAY_NEW = 44;
	/** 订单等待首付，对于有首付的订单。 **/
	public final static int STATUS_WAIT_FIRST_PAY = 4;
	/** 首付完成。 **/
	public final static int STATUS_FIRST_PAY_DONE = 5;
	/** 等待发货，运营外部订单生成成功。 **/
	public final static int STATUS_WAIT_DELIVER = 6;
	/** 已经发货 **/
	public final static int STATUS_HAVE_DELIVER = 7;
	/** 还款中 **/
	public final static int STATUS_PAYING = 8;
	/** 还款完成 **/
	public final static int STATUS_PAY_DONE = 9;
	/** 订单取消 **/
	public final static int STATUS_ORDER_CANCEL = 21;
	/** 订单无效 **/
	public final static int STATUS_ORDER_INVALID = 22;
	/** 订单删除 **/
	public final static int STATUS_ORDER_DELETE = 23;

    public final static int STATUS_ORDER_SKU_NOT_RELEASE = 30;
    
    /** 预约订单，订单可以取消 **/
    public final static int STATUS_ORDER_APPOINTMENT = 99;
    /** 预约订单取消 **/
    public final static int STATUS_ORDER_APPOINTMENT_CANCEL = 98;
    /** 预约订单删除 **/
    public final static int STATUS_ORDER_APPOINTMENT_DELETE = 97;

	/** 订单状态 **/
	private int status;

	/** 首付 **/
	private double downPayment;

	/** 分期数 **/
	private int stages;

	/** 每期本金 **/
	private double perCapital;

	/** 每期利息 **/
	private double perInterest;

	/** 订单创建时间 **/
	private Date createTime;

	/** 商品id **/
	private String goodsId = "";

	/** skuId **/
	private String skuId = "";

	/** 用户提交外链商品链接 **/
	private String linkUrl = "";

	/** 用户提交外链商品备注 **/
	private String userRemarks = "";

	/** 收货地址 **/
	private String address = "";
	

	/* 订单额度 */
	/** 小额订单 **/
	public final static int LARGE_ORDER_NO = 0;
	/** 大额订单 **/
	public final static int LARGE_ORDER_YES = 1;

	/** 大额订单标识 **/
	private int largeOrder;

	/** 上传的额外信誉支付图片 **/
	private String payCreditImg = "";

	/** 上传了额外信誉支付图片 **/
	public final static int UPLOAD_CREDIT_IMG_NO = 0;
	/** 没有上传额外信誉支付图片 **/
	public final static int UPLOAD_CREDIT_IMG_YES = 1;

	/** 上传的额外信誉支付图片标识，没啥用 **/
	private int uplodCreditImg;

	/** 首付比例，对应了temp1字段 */
	private int payRate;

	private String kuaidiName = "";

	private String kuaidiOrder = "";

	private double kuaidiPrice;

	private double manjianPrice;

	private String hongbaoTypeList = "";

	private double hongbaoPrice;

	private String contractNum;

	private int userCouponsId;
	private double couponsPrice;
	private int sellCode;
	private int couponsType;
	private int actId;
	private Date payTime;
	private Date auditTime;
	private double reduceIntrRate;
	private Date deliverTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {

		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAttrInfo() {
		return attrInfo;
	}

	public void setAttrInfo(String attrInfo) {
		this.attrInfo = attrInfo;
	}

	public double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(double downPayment) {
		this.downPayment = downPayment;
	}

	public int getStages() {
		return stages;
	}

	public void setStages(int stages) {
		this.stages = stages;
	}

	public double getPerCapital() {
		return perCapital;
	}

	public void setPerCapital(double perCapital) {
		this.perCapital = perCapital;
	}

	public double getPerInterest() {
		return perInterest;
	}

	public void setPerInterest(double perInterest) {
		this.perInterest = perInterest;
	}

	public double getSkuPrice() {
		return skuPrice;
	}

	public void setSkuPrice(double skuPrice) {
		this.skuPrice = skuPrice;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getSkuLogo() {
		return skuLogo;
	}

	public void setSkuLogo(String skuLogo) {
		this.skuLogo = skuLogo;
	}

	public int getSkuNum() {
		return skuNum;
	}

	public void setSkuNum(int skuNum) {
		this.skuNum = skuNum;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getUserRemarks() {
		return userRemarks;
	}

	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getLargeOrder() {
		return largeOrder;
	}

	public void setLargeOrder(int largeOrder) {
		this.largeOrder = largeOrder;
	}

	public String getPayCreditImg() {
		return payCreditImg;
	}

	public void setPayCreditImg(String payCreditImg) {
		this.payCreditImg = payCreditImg;
	}

	public int getUplodCreditImg() {
		return uplodCreditImg;
	}

	public void setUplodCreditImg(int uplodCreditImg) {
		this.uplodCreditImg = uplodCreditImg;
	}

	/**
	 * 从订单列表中取出自营商品订单信息
	 * 
	 * @return
	 */
	

	

	public int getPayRate() {
		return payRate;
	}

	public void setPayRate(int payRate) {
		this.payRate = payRate;
	}

	public String getKuaidiName() {
		return kuaidiName;
	}

	public void setKuaidiName(String kuaidiName) {
		this.kuaidiName = kuaidiName;
	}

	public String getKuaidiOrder() {
		return kuaidiOrder;
	}

	public void setKuaidiOrder(String kuaidiOrder) {
		this.kuaidiOrder = kuaidiOrder;
	}

	public double getKuaidiPrice() {
		return kuaidiPrice;
	}

	public void setKuaidiPrice(double kuaidiPrice) {
		this.kuaidiPrice = kuaidiPrice;
	}

	public List<String> getPayCreditImgList() {
		if (!Strings.isNullOrEmpty(payCreditImg)) {
			List<String> list = new ArrayList<String>();
			String[] imgArr = payCreditImg.split(";");
			for (String url : imgArr) {
				list.add(url);
			}
			return list;
		}
		return Collections.emptyList();
	}

	public double getManjianPrice() {
		return manjianPrice;
	}

	public void setManjianPrice(double manjianPrice) {
		this.manjianPrice = manjianPrice;
	}

	public String getHongbaoTypeList() {
		return hongbaoTypeList;
	}

	public void setHongbaoTypeList(String hongbaoTypeList) {
		this.hongbaoTypeList = hongbaoTypeList;
	}

	public double getHongbaoPrice() {
		return hongbaoPrice;
	}

	public void setHongbaoPrice(double hongbaoPrice) {
		this.hongbaoPrice = hongbaoPrice;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public int getUserCouponsId() {
		return userCouponsId;
	}

	public void setUserCouponsId(int userCouponsId) {
		this.userCouponsId = userCouponsId;
	}

	public double getCouponsPrice() {
		return couponsPrice;
	}

	public void setCouponsPrice(double couponsPrice) {
		this.couponsPrice = couponsPrice;
	}



	public int getCouponsType() {
		return couponsType;
	}

	public void setCouponsType(int couponsType) {
		this.couponsType = couponsType;
	}

	
	public int getActId() {
		return actId;
	}

	public void setActId(int actId) {
		this.actId = actId;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public double getReduceIntrRate() {
		return reduceIntrRate;
	}

	public void setReduceIntrRate(double reduceIntrRate) {
		this.reduceIntrRate = reduceIntrRate;
	}

	public Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	public int getSellCode() {
		return sellCode;
	}

	public void setSellCode(int sellCode) {
		this.sellCode = sellCode;
	}

}
