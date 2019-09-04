package com.davidredondo.dto.rules;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.joda.time.Seconds;

import com.davidredondo.dto.BillingPortion;
import com.davidredondo.dto.BillingShift;
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
		Seconds billingStartSeconds = Seconds.seconds(billingShift.getStartDateTime().getSecondOfDay());
		
		Seconds secondsBetweenRuleStartAndEnd = getSecondsBetweenStartAndEnd();
		
		
		// Se convierte en un duration
		//Seconds start = Seconds.ZERO;
		Seconds start = startSeconds.minus(billingStartSeconds);
		if (start.isLessThan(Seconds.ZERO)) {
			secondsBetweenRuleStartAndEnd = secondsBetweenRuleStartAndEnd.plus(start);
			start = Seconds.ZERO;
		}
		Seconds end = start.plus(secondsBetweenRuleStartAndEnd);
		/* BORRAR */
		BillingPortion billingPortion = new BillingPortion(); 
		Integer sessionDuration = calculatePortionSessionDuration(billingShift.getSessionTime().getSeconds(), start.getSeconds(), end.getSeconds());
		billingPortion.setSession(sessionDuration);
		billingPortion.setPay(Double.valueOf(DateUtils.secondsToHours(sessionDuration)) * this.getPayRate());
		billingPortion.setId(this.getId());
		billingPortion.setStart(DateUtils.getJsonDateFormatFromDateTime(billingShift.getStartDateTime().plus(start)));
		billingPortion.setEnd(DateUtils.getJsonDateFormatFromDateTime(billingShift.getStartDateTime().plus(start).plus(Seconds.seconds(sessionDuration))));
		return billingPortion;
		
	}

	/* DELETE */
	private Integer calculatePortionSessionDuration(Integer shiftDuration, Integer start, Integer end) {
		if (start > shiftDuration) {
			return 0;
		} else if( end > shiftDuration ) {
			return shiftDuration - start;
		} else if (end < shiftDuration ) {
			return end - start;
		} 
		return 0;
	}

	private Seconds getSecondsBetweenStartAndEnd() {
		if (endSeconds.isGreaterThan(startSeconds)) {
			return endSeconds.minus(startSeconds);
		} else {
			return endSeconds.plus(DateUtils.SECONDS_IN_A_DAY).minus(startSeconds);
		}
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

}
