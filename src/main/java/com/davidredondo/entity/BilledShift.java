package com.davidredondo.entity;

import java.io.Serializable;
import java.util.List;

import com.davidredondo.util.DecimalWithTwoDigitPrecision;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class BilledShift implements Serializable {
	
	private static final long serialVersionUID = -9036942206048040725L;

	private Integer id;
	
	private String start;
	
	private String end;
	
	private Integer session;
	
	@JsonSerialize(using = DecimalWithTwoDigitPrecision.class)
	private Double pay;
	
	private List<BillingPortion> portions;
	
	public static BilledShift from(BillingShift billingShift, List<BillingPortion> billingPortionList) {
		BilledShift billedShift = new BilledShift();
		billedShift.setId(billingShift.getId());
		billedShift.setStart(billingShift.getStart());
		billedShift.setEnd(billingShift.getEnd());
		billedShift.setPortions(billingPortionList);
		billedShift.setPay(billingPortionList.stream().map(BillingPortion::getPay).reduce(0d, Double::sum));
		billedShift.setSession(billingShift.getSessionInSeconds());
		return billedShift;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}


	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Integer getSession() {
		return session;
	}

	public void setSession(Integer session) {
		this.session = session;
	}

	public Double getPay() {
		return pay;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}

	public List<BillingPortion> getPortions() {
		return portions;
	}

	public void setPortions(List<BillingPortion> portions) {
		this.portions = portions;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof BilledShift) {
			BilledShift billedShift = BilledShift.class.cast(obj);
			return billedShift.id.equals(this.id) && billedShift.start.equals(this.start) && billedShift.end.equals(this.end )&& billedShift.session.equals(this.session);
		}
		return false;
	}

}
