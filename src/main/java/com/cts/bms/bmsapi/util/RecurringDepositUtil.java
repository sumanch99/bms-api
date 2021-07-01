package com.cts.bms.bmsapi.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecurringDepositUtil {
	private static final Logger LOGGER = LogManager.getLogger(RecurringDepositUtil.class);

	/* Estimation of the matured amount */
	public static double getMaturedAmount(double monthlyInvestment, double rateOfInterest, int tenure) {
		LOGGER.info("START");
		double interestAmount = monthlyInvestment * (tenure*(tenure+1)/(12*2)) * (rateOfInterest/100);
		double maturedAmount = monthlyInvestment*tenure + interestAmount;
		
		LOGGER.info("END");
		return maturedAmount;
	}

}
