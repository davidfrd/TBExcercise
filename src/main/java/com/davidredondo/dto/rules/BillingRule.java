package com.davidredondo.dto.rules;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.davidredondo.dto.BillingPortion;
import com.davidredondo.dto.BillingShift;
import com.davidredondo.util.DoubleDecimalSerializerWithTwoDigitPrecisionAndDotSeparator;
import com.davidredondo.util.interfaces.Validable;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = FixedBillingRule.class, name = "FIXED"),
    @JsonSubTypes.Type(value = DurationBillingRule.class, name = "DURATION")
})
public abstract class BillingRule implements Validable, Serializable {
	
	private static final long serialVersionUID = -4881567644190448278L;

	@NotNull
	@Positive
	private Integer id;
	
	@NotNull
	@NotEmpty
	private String type;
	
	@NotNull
	@Positive
	@JsonSerialize(using = DoubleDecimalSerializerWithTwoDigitPrecisionAndDotSeparator.class)
	private Double payRate;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPayRate() {
		return payRate;
	}

	public void setPayRate(Double payRate) {
		this.payRate = payRate;
	}
	
	public abstract BillingPortion calculateBillingPortionFromShift(BillingShift billingShift);

}
