package com.davidredondo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.davidredondo.entity.BilledShift;
import com.davidredondo.entity.BillingPortion;
import com.davidredondo.entity.BillingShift;
import com.davidredondo.entity.request.BillingRequest;
import com.davidredondo.entity.response.BillingResponse;
import com.davidredondo.entity.rules.BillingRule;
import com.davidredondo.entity.rules.DurationBillingRule;
import com.davidredondo.entity.rules.FixedBillingRule;
import com.davidredondo.service.interfaces.IBillingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillingServiceTests {
	
	BillingRequest billingRequest;
	
	BillingResponse billingResponse;
	
	@Autowired
	IBillingService billingService;
	
	@Before
	public void initBillingRequest() {
		billingRequest = new BillingRequest();
		billingRequest.setRules(createBillingRules());
		billingRequest.setShifts(createBillingShifts());
	}
	
	@Before
	public void initBillingResponse() {
		billingResponse = new BillingResponse();
		billingResponse.setPay(152.21);
		billingResponse.setBilledShifts(createBilledShifts());
		
	}
	
	@Test
	public void testCalculateBilling() {
		BillingResponse serviceBillingResponse = billingService.calculateBilling(billingRequest);
		assertEquals(serviceBillingResponse, this.billingResponse);
	}

	private List<BilledShift> createBilledShifts() {
		List<BilledShift> billedShifts = new ArrayList<>();
		billedShifts.add(createFirstBilledShift());
		billedShifts.add(createSecondBilledShift());
		return billedShifts;
	}

	private BilledShift createFirstBilledShift() {
		BilledShift billedShift = new BilledShift();
		billedShift.setId(1);
		billedShift.setStart("2019-04-28 20:30:00");
		billedShift.setEnd("2019-04-29 00:30:00");
		billedShift.setSession(14400);
		billedShift.setPay(82.23);
		billedShift.setPortions(createFirstBillingPortions());
		return billedShift;
	}

	private List<BillingPortion> createFirstBillingPortions() {
		List<BillingPortion> portions = new ArrayList<>();
		portions.add(createBillingPortion(1, "2019-04-28 21:00:00", "2019-04-29 00:00:00", 10800, 31.50));
		portions.add(createBillingPortion(2, "2019-04-28 21:30:00", "2019-04-28 22:30:00", 3600, 20.23));
		portions.add(createBillingPortion(3, "2019-04-28 22:30:00", "2019-04-28 23:30:00", 3600, 30.50));
		return portions;
	}
	
	private BilledShift createSecondBilledShift() {
		BilledShift billedShift = new BilledShift();
		billedShift.setId(2);
		billedShift.setStart("2019-04-29 22:10:00");
		billedShift.setEnd("2019-04-30 02:15:00");
		billedShift.setSession(14700);
		billedShift.setPay(69.98);
		billedShift.setPortions(createSecondBillingPortions());
		return billedShift;
	}
	
	private List<BillingPortion> createSecondBillingPortions() {
		List<BillingPortion> portions = new ArrayList<>();
		portions.add(createBillingPortion(1, "2019-04-29 22:10:00", "2019-04-30 00:00:00", 6600, 19.25));
		portions.add(createBillingPortion(2, "2019-04-29 23:10:00", "2019-04-30 00:10:00", 3600, 20.23));
		portions.add(createBillingPortion(3, "2019-04-30 00:10:00", "2019-04-30 01:10:00", 3600, 30.50));
		return portions;
	}

	private BillingPortion createBillingPortion(Integer id, String start, String end, Integer session, Double pay) {
		BillingPortion billingPortion = new BillingPortion();
		billingPortion.setId(id);
		billingPortion.setStart(start);
		billingPortion.setEnd(end);
		billingPortion.setSession(session);
		billingPortion.setPay(pay);
		return billingPortion;
	}
	
	private List<BillingShift> createBillingShifts() {
		List<BillingShift> shifts = new ArrayList<>();
		shifts.add(createBillingShift(1, "2019-04-28 20:30:00", "2019-04-29 00:30:00"));
		shifts.add(createBillingShift(2, "2019-04-29 22:10:00", "2019-04-30 02:15:00"));
		return shifts;
	}
	
	private BillingShift createBillingShift(Integer id, String start, String end) {
		BillingShift billingShift = new BillingShift();
		billingShift.setId(id);
		billingShift.setStart(start);
		billingShift.setEnd(end);
		return billingShift;
	}

	private List<BillingRule> createBillingRules() {
		List<BillingRule> rules = new ArrayList<>();
		rules.add(createFixedBillingRule());
		rules.add(createFirstDurationBillingRule());
		rules.add(createSecondDurationBillingRule());
		return rules;
	}

	private BillingRule createFixedBillingRule() {
		FixedBillingRule billingRule = new FixedBillingRule();
		billingRule.setId(1);
		billingRule.setType("FIXED");
		billingRule.setStart("21:00");
		billingRule.setEnd("00:00");
		billingRule.setPayRate(10.50);
		return billingRule;
	}
	
	private BillingRule createFirstDurationBillingRule() {
		DurationBillingRule billingRule = new DurationBillingRule();
		billingRule.setId(2);
		billingRule.setType("DURATION");
		billingRule.setStart(3600);
		billingRule.setEnd(7200);
		billingRule.setPayRate(20.23);
		return billingRule;
	}
	
	private BillingRule createSecondDurationBillingRule() {
		DurationBillingRule billingRule = new DurationBillingRule();
		billingRule.setId(3);
		billingRule.setType("DURATION");
		billingRule.setStart(7200);
		billingRule.setEnd(10800);
		billingRule.setPayRate(30.50);
		return billingRule;
	}

}
