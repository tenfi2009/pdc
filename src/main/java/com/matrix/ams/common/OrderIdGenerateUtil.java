package com.matrix.ams.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderIdGenerateUtil {
    /**
     * 
     * 订单id的生成方法，当前时间格式化("yyMMddHHmmssSSS") * 10000 + userId%10000
     * @param userId
     */
    public static String genOrderId(int userId){
        /*
         * 2014-09-01 的timestamp，作为参照。缩短订单号长度。
         * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         * Date date = sdf.parse("2014-09-01");
         * date.getTime();
         */
//    	Date d = new Date(System.currentTimeMillis());
//    	SimpleDateFormat SDF = new SimpleDateFormat("yyMMddHHmmssSSS");
//    	String date = SDF.format(d);
    	
//        long timeStampRefer = long timeStampRefer = 1409500800000l;;
//        
//        long nowTime = (new Date()).getTime();
        //生成订单号,
    	
    	long startTime = 1390436700000l;
    	long cha = (long)(System.currentTimeMillis() - startTime)/100;
//        Long id = Long.valueOf(date);
        String orderId = String.valueOf(cha * 100 + userId % 100) ;
        
        return orderId;
    }
    
    public static void main(String [] args) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse("2014-01-23 08:25");
    	Date date1 = sdf.parse("2017-03-23 08:25");
    	System.out.println(date1.getTime() - date.getTime());
        System.out.println(OrderIdGenerateUtil.genOrderId(10001));
        System.out.println(System.currentTimeMillis() - date.getTime());
        System.out.println(genOrderId(18232));
    }
}
