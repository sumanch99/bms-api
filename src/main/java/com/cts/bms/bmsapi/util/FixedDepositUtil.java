package com.cts.bms.bmsapi.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedDepositUtil {
	
	private static final Logger logger=LogManager.getLogger(FixedDepositUtil.class);
	/*Estimation of the matured amount*/
	public static double getMaturedAmount(double principalAmount,double rateOfInterest,int tenure) {
		logger.info("START");
		int tenureInYears = tenure/12;
		double totalMaturdAmount = principalAmount*Math.pow( (1 + rateOfInterest / (100*12) ),(tenureInYears*12));
		logger.info("END");
		return  totalMaturdAmount;
	}
	
}
