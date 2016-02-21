package com.matrix.aviator.ext.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.aviator.AviatorEvaluator;

/**
 * 函数初始化服务
 * @author rong yang
 *
 */

public class AviatorFunctionInitService {
	private static Logger logger = LoggerFactory.getLogger(AviatorFunctionInitService.class);

	public AviatorFunctionInitService() {
		super();
	}

	public void init() {
		/**注册自定义服务**/
		logger.info("开始注册Aviator自定义函数。。。");
		AviatorEvaluator.addFunction(new MathSignFunction());
		AviatorEvaluator.addFunction(new DecodeFunction());
		
		logger.info("注册Aviator自定义函数成功！");
	}
}
