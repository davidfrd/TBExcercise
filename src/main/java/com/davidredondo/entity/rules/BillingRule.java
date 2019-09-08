package com.davidredondo.entity.rules;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.davidredondo.entity.BillingPortion;
import com.davidredondo.entity.BillingShift;
import com.davidredondo.util.DecimalWithTwoDigitPrecision;
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
	protected Integer id;
	
	@NotNull
	@NotEmpty
	protected String type;
	
	@NotNull
	@Positive
	@JsonSerialize(using = DecimalWithTwoDigitPrecision.class)
	protected Double payRate;

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

	public boolean equals(Object obj) {
		if (obj instanceof BillingRule) {
			BillingRule billingRule = BillingRule.class.cast(obj);
			return super.equals(obj) && billingRule.id.equals(this.id) && billingRule.type.equals(this.type);
		}
		return false;
	}
}
