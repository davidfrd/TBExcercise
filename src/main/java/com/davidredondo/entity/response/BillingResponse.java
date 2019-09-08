package com.davidredondo.entity.response;

import java.io.Serializable;
import java.util.List;

import com.davidredondo.entity.BilledShift;
import com.davidredondo.entity.rules.BillingRule;
import com.davidredondo.util.DecimalWithTwoDigitPrecision;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class BillingResponse implements Serializable {
	
	private static final long serialVersionUID = 5997136200516270281L;

	@JsonSerialize(using = DecimalWithTwoDigitPrecision.class)
	private Double pay;
	
	private List<BilledShift> billedShifts;
	
	public static BillingResponse from(List<BilledShift> billedShiftList) {
		BillingResponse billingResponse = new BillingResponse();
		billingResponse.billedShifts = billedShiftList;
		billingResponse.pay = billedShiftList.stream().map(BilledShift::getPay).reduce(0d, Double::sum);
		return billingResponse;
	}

	public Double getPay() {
		return pay;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}

	public List<BilledShift> getBilledShifts() {
		return billedShifts;
	}

	public void setBilledShifts(List<BilledShift> billedShifts) {
		this.billedShifts = billedShifts;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof BillingResponse) {
			BillingResponse billingResponse = BillingResponse.class.cast(obj);
			return billingResponse.pay.equals(this.pay) && billingResponse.billedShifts.equals(this.billedShifts);
		}
		return false;
	}

}
