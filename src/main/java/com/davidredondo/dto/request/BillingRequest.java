package com.davidredondo.dto.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.davidredondo.dto.BillingShift;
import com.davidredondo.dto.rules.BillingRule;
import com.davidredondo.util.interfaces.Validable;

public class BillingRequest implements Validable, Serializable {
	
	private static final long serialVersionUID = -2480859132158491986L;

	@NotNull
	private List<BillingRule> rules;
	
	@NotNull
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
	public boolean validate() {
		return validateBillingRules() && validateBillingShifts();
	}
	
	private boolean validateBillingRules() {
		return rules.stream().allMatch(Validable::validate);
	}
	
	private boolean validateBillingShifts() {
		return shifts.stream().allMatch(Validable::validate);
	}
	
	

}
