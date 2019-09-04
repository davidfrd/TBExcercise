package com.davidredondo.dto;

import com.davidredondo.util.DoubleDecimalSerializerWithTwoDigitPrecisionAndDotSeparator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class BillingPortion {

	private Integer id;

	private String start;

	private String end;

	private Integer session;

	@JsonSerialize(using = DoubleDecimalSerializerWithTwoDigitPrecisionAndDotSeparator.class)
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


}
