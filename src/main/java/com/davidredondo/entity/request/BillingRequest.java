package com.davidredondo.entity.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.davidredondo.entity.BillingShift;
import com.davidredondo.entity.rules.BillingRule;
import com.davidredondo.util.interfaces.Validable;

public class BillingRequest implements Validable, Serializable {
	
	private static final long serialVersionUID = -2480859132158491986L;

	@NotNull
	@Valid
	private List<BillingRule> rules;
	
	@NotNull
	@Valid
	private List<BillingShift> shifts;

	public List<BillingRule> getRules() {
		return rules;
	}

	public void setRules(List<BillingRule> rules) {
		this.rules = rules;
	}

	public List<BillingShift> getShifts() {
		return shifts;
	}

	public void setShifts(List<BillingShift> shifts) {
		this.shifts = shifts;
	}

	@Override
	public void validate() {
		validateBillingRules();
		validateBillingShifts();
	}
	
	private void validateBillingRules() {
		rules.stream().forEach(Validable::validate);
	}
	
	private void validateBillingShifts() {
		shifts.stream().forEach(Validable::validate);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof BillingRequest) {
			BillingRequest billingRequest = BillingRequest.class.cast(obj);
			return billingRequest.shifts.equals(this.shifts) && billingRequest.rules.equals(this.rules);
		}
		return false;
	}
	
}
