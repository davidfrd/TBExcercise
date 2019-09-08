package com.davidredondo.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.davidredondo.entity.BilledShift;
import com.davidredondo.entity.BillingPortion;
import com.davidredondo.entity.BillingShift;
import com.davidredondo.entity.EntityMaker;
import com.davidredondo.entity.request.BillingRequest;
import com.davidredondo.entity.response.BillingResponse;
import com.davidredondo.entity.rules.BillingRule;
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
		billedShifts.add(EntityMaker.createBilledShift(1, "2019-04-28 20:30:00", "2019-04-29 00:30:00", 14400, 82.23, createFirstBillingPortions()));
		billedShifts.add(EntityMaker.createBilledShift(2, "2019-04-29 22:10:00", "2019-04-30 02:15:00", 14700, 69.98, createSecondBillingPortions()));
		return billedShifts;
	}

	private List<BillingPortion> createFirstBillingPortions() {
		List<BillingPortion> portions = new ArrayList<>();
		portions.add(EntityMaker.createBillingPortion(1, "2019-04-28 21:00:00", "2019-04-29 00:00:00", 10800, 31.50));
		portions.add(EntityMaker.createBillingPortion(2, "2019-04-28 21:30:00", "2019-04-28 22:30:00", 3600, 20.23));
		portions.add(EntityMaker.createBillingPortion(3, "2019-04-28 22:30:00", "2019-04-28 23:30:00", 3600, 30.50));
		return portions;
	}
	
	
	private List<BillingPortion> createSecondBillingPortions() {
		List<BillingPortion> portions = new ArrayList<>();
		portions.add(EntityMaker.createBillingPortion(1, "2019-04-29 22:10:00", "2019-04-30 00:00:00", 6600, 19.25));
		portions.add(EntityMaker.createBillingPortion(2, "2019-04-29 23:10:00", "2019-04-30 00:10:00", 3600, 20.23));
		portions.add(EntityMaker.createBillingPortion(3, "2019-04-30 00:10:00", "2019-04-30 01:10:00", 3600, 30.50));
		return portions;
	}

	
	private List<BillingShift> createBillingShifts() {
		List<BillingShift> shifts = new ArrayList<>();
		shifts.add(EntityMaker.createBillingShift(1, "2019-04-28 20:30:00", "2019-04-29 00:30:00"));
		shifts.add(EntityMaker.createBillingShift(2, "2019-04-29 22:10:00", "2019-04-30 02:15:00"));
		return shifts;
	}
	

	private List<BillingRule> createBillingRules() {
		List<BillingRule> rules = new ArrayList<>();
		rules.add(EntityMaker.createFixedBillingRule(1, "21:00", "00:00", 10.50));
		rules.add(EntityMaker.createDurationBillingRule(2, 3600, 7200, 20.23));
		rules.add(EntityMaker.createDurationBillingRule(3, 7200, 10800, 30.50));
		return rules;
	}

}
