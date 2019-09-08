package com.davidredondo.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.davidredondo.entity.BillingPortion;
import com.davidredondo.entity.BillingShift;
import com.davidredondo.entity.rules.DurationBillingRule;
import com.davidredondo.entity.rules.FixedBillingRule;
import com.davidredondo.exception.ValidationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillingRuleTests {

	BillingShift shift;
	FixedBillingRule fixedRule;
	DurationBillingRule durationRule;
	BillingPortion billingPortion ;

	@Before
	public void initialize() {
		shift = createBillingShift(1, "2019-04-28 08:00:00", "2019-04-28 17:00:00");
		fixedRule = createFixedBillingRule(1, "15:00", "18:00", 10.5);
		durationRule = createFixedBillingRule(1, 25200, 36000, 10.5);
		billingPortion = createBillingPortion(1, "2019-04-28 15:00:00", "2019-04-28 17:00:00", 7200, 21d);
	}

	@Test
	public void testFixedRuleToDurationConvertionEquivalency() {
		assertEquals(fixedRule.calculateBillingPortionFromShift(shift),
				durationRule.calculateBillingPortionFromShift(shift));
	}

	@Test
	public void testFixedRuleCalculation() {
		assertEquals(fixedRule.calculateBillingPortionFromShift(shift), billingPortion);
	}

	@Test
	public void testDurationRuleCalculation() {
		assertEquals(durationRule.calculateBillingPortionFromShift(shift), billingPortion);
	}

	@Test(expected = ValidationException.class)
	public void testDurationRuleValidation() {
		DurationBillingRule badDurationRule = createFixedBillingRule(1, 25200, 0, 10.50);
		badDurationRule.validate();
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

	private FixedBillingRule createFixedBillingRule(Integer id, String start, String end, Double payRate) {
		FixedBillingRule billingRule = new FixedBillingRule();
		billingRule.setId(id);
		billingRule.setType("FIXED");
		billingRule.setStart(start);
		billingRule.setEnd(end);
		billingRule.setPayRate(payRate);
		return billingRule;
	}

	private DurationBillingRule createFixedBillingRule(Integer id, Integer start, Integer end, Double payRate) {
		DurationBillingRule billingRule = new DurationBillingRule();
		billingRule.setId(id);
		billingRule.setType("DURATION");
		billingRule.setStart(start);
		billingRule.setEnd(end);
		billingRule.setPayRate(payRate);
		return billingRule;
	}

	private BillingShift createBillingShift(Integer id, String start, String end) {
		BillingShift billingShift = new BillingShift();
		billingShift.setId(id);
		billingShift.setStart(start);
		billingShift.setEnd(end);
		return billingShift;
	}

}
