package com.davidredondo.dto.rules;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.joda.time.Seconds;

import com.davidredondo.dto.BillingPortion;
import com.davidredondo.dto.BillingShift;
import com.davidredondo.util.DateUtils;

public class DurationBillingRule extends BillingRule {
	
	private static final long serialVersionUID = -2990691404971868280L;

	@NotNull
	@PositiveOrZero
	private Integer start;
	
	@NotNull
	@Positive
	private Integer end;
	
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public BillingPortion calculateBillingPortionFromShift(BillingShift billingShift) {
		BillingPortion billingPortion = new BillingPortion(); 
		Integer sessionDuration = calculatePortionSessionDuration(billingShift.getSessionTime().getSeconds());
		billingPortion.setSession(sessionDuration);
		billingPortion.setPay(Double.valueOf(DateUtils.secondsToHours(sessionDuration)) * this.getPayRate());
		billingPortion.setId(this.getId());
		billingPortion.setStart(DateUtils.getJsonDateFormatFromDateTime(billingShift.getStartDateTime().plus(Seconds.seconds(start))));
		billingPortion.setEnd(DateUtils.getJsonDateFormatFromDateTime(billingShift.getStartDateTime().plus(Seconds.seconds(start)).plus(Seconds.seconds(sessionDuration))));
		return billingPortion;
	}

	private Integer calculatePortionSessionDuration(Integer shiftDuration) {
		if (start > shiftDuration) {
			return 0;
		} else if( end > shiftDuration) {
			return shiftDuration - start;
		} else if (end < shiftDuration ) {
			return end - start;
		} 
		return 0;
	}


	@Override
	public boolean validate() {
		return ruleStartsBeforeEnds();
	}
	
	private boolean ruleStartsBeforeEnds() {
		return start < end;
	}

}
