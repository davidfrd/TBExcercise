package com.davidredondo.entity;

import java.util.List;

import com.davidredondo.entity.rules.DurationBillingRule;
import com.davidredondo.entity.rules.FixedBillingRule;

public class EntityMaker {
	
	private EntityMaker() {}

	public static BillingPortion createBillingPortion(Integer id, String start, String end, Integer session, Double pay) {
		BillingPortion billingPortion = new BillingPortion();
		billingPortion.setId(id);
		billingPortion.setStart(start);
		billingPortion.setEnd(end);
		billingPortion.setSession(session);
		billingPortion.setPay(pay);
		return billingPortion;
	}

	public static FixedBillingRule createFixedBillingRule(Integer id, String start, String end, Double payRate) {
		FixedBillingRule billingRule = new FixedBillingRule();
		billingRule.setId(id);
		billingRule.setType("FIXED");
		billingRule.setStart(start);
		billingRule.setEnd(end);
		billingRule.setPayRate(payRate);
		return billingRule;
	}

	public static DurationBillingRule createDurationBillingRule(Integer id, Integer start, Integer end, Double payRate) {
		DurationBillingRule billingRule = new DurationBillingRule();
		billingRule.setId(id);
		billingRule.setType("DURATION");
		billingRule.setStart(start);
		billingRule.setEnd(end);
		billingRule.setPayRate(payRate);
		return billingRule;
	}

	public static BillingShift createBillingShift(Integer id, String start, String end) {
		BillingShift billingShift = new BillingShift();
		billingShift.setId(id);
		billingShift.setStart(start);
		billingShift.setEnd(end);
		return billingShift;
	}
	
	public static BilledShift createBilledShift(Integer id, String start, String end, Integer session, Double pay, List<BillingPortion> billingPortions) {
		BilledShift billedShift = new BilledShift();
		billedShift.setId(id);
		billedShift.setStart(start);
		billedShift.setEnd(end);
		billedShift.setSession(session);
		billedShift.setPay(pay);
		billedShift.setPortions(billingPortions);
		return billedShift;
	}
	
}
