package com.davidredondo.entity.rules;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.joda.time.Seconds;

import com.davidredondo.entity.BilledShift;
import com.davidredondo.entity.BillingPortion;
import com.davidredondo.entity.BillingShift;
import com.davidredondo.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class FixedBillingRule extends BillingRule {
	
	private static final long serialVersionUID = 4094509868737612763L;

	@NotNull
	@NotEmpty
	private String start;
	
	@NotNull
	@NotEmpty
	private String end;
	
	@JsonIgnore
	private Seconds startSeconds;
	
	@JsonIgnore
	private Seconds endSeconds;
	
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.startSeconds = DateUtils.getSecondsFromJsonHourFormat(start);
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.endSeconds = DateUtils.getSecondsFromJsonHourFormat(end);
		this.end = end;
	}

	public BillingPortion calculateBillingPortionFromShift(BillingShift billingShift) {
		return convertToDurationBillingRule(billingShift).calculateBillingPortionFromShift(billingShift);
	}
	
	private DurationBillingRule convertToDurationBillingRule(BillingShift billingShift) {
		Seconds billingStartSeconds = Seconds.seconds(billingShift.getStartDateTime().getSecondOfDay());
		Seconds secondsBetweenRuleStartAndEnd = getSecondsBetweenStartAndEnd();
		Seconds start = startSeconds.minus(billingStartSeconds);
		if (start.isLessThan(Seconds.ZERO)) {
			secondsBetweenRuleStartAndEnd = secondsBetweenRuleStartAndEnd.plus(start);
			start = Seconds.ZERO;
		}
		Seconds end = start.plus(secondsBetweenRuleStartAndEnd);
		DurationBillingRule durationBillingRule = new DurationBillingRule();
		durationBillingRule.setId(this.getId());
		durationBillingRule.setPayRate(this.getPayRate());
		durationBillingRule.setStart(start.getSeconds());
		durationBillingRule.setEnd(end.getSeconds());
		return durationBillingRule;
		
	}

	private Seconds getSecondsBetweenStartAndEnd() {
		if (endSeconds.isGreaterThan(startSeconds)) {
			return endSeconds.minus(startSeconds);
		} else {
			return endSeconds.plus(DateUtils.SECONDS_IN_A_DAY).minus(startSeconds);
		}
	}

	@Override
	public void validate() {}
	
	public boolean equals(Object obj) {
		if (obj instanceof FixedBillingRule) {
			FixedBillingRule fixedBillingRule = FixedBillingRule.class.cast(obj);
			return super.equals(obj) && fixedBillingRule.start.equals(this.start) && fixedBillingRule.end.equals(this.end);
		}
		return false;
	}

}
