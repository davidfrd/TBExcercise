package com.davidredondo.dto;

import java.io.Serializable;
import java.util.List;

public class BilledShift implements Serializable {
	
	private static final long serialVersionUID = -9036942206048040725L;

	private Integer id;
	
	private String start;
	
	private String end;
	
	private Integer session;
	
	private Double pay;
	
	private List<BillingPortion> portions;
	
	public static BilledShift from(BillingShift billingShift, List<BillingPortion> billingPortionList) {
		BilledShift billedShift = new BilledShift();
		billedShift.id = billingShift.getId();
		billedShift.start = billingShift.getStart();
		billedShift.end = billingShift.getEnd();
		billedShift.portions = billingPortionList;
		billedShift.pay = billingPortionList.stream().map(BillingPortion::getPay).reduce(0d, Double::sum);
		billedShift.session = billingShift.getSessionInSeconds();
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

}
