package com.davidredondo.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.davidredondo.exception.ValidationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillingShiftTests {
	
	@Test(expected = ValidationException.class)
	public void testBillingShiftStartsAfterEndsValidation() {
		BillingShift billingShift = new BillingShift();
		billingShift.setId(1);
		billingShift.setStart("2019-04-28 20:30:00");
		billingShift.setEnd("2019-04-27 20:30:00");
		billingShift.validate();
	}
	
	@Test(expected = ValidationException.class)
	public void testBillingShiftDurationValidation() {
		BillingShift billingShift = new BillingShift();
		billingShift.setId(1);
		billingShift.setStart("2019-04-28 20:30:00");
		billingShift.setEnd("2020-04-28 20:30:00");
		billingShift.validate();
	}

}
