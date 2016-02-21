/**
 * 
 */
package com.matrix.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 还款相关接口
 * @author rongyang
 *
 */
@RestController
@RequestMapping(value = "/api/repay")
public class RepayServiceController {
	private static Logger logger = LoggerFactory.getLogger(RepayServiceController.class);
}
