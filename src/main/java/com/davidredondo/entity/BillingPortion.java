package com.davidredondo.entity;

import com.davidredondo.util.DecimalWithTwoDigitPrecision;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class BillingPortion {

	private Integer id;

	private String start;

	private String end;

	private Integer session;

	@JsonSerialize(using = DecimalWithTwoDigitPrecision.class)
	private Double pay;

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

	public boolean equals(Object obj) {
		if (obj instanceof BillingPortion) {
			BillingPortion billingPortion = BillingPortion.class.cast(obj);
			return billingPortion.id.equals(this.id) && billingPortion.start.equals(this.start)
					&& billingPortion.end.equals(this.end) && billingPortion.session.equals(this.session);
		}
		return false;
	}

}
