package com.davidredondo.entity.rules;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.joda.time.Seconds;

import com.davidredondo.entity.BillingPortion;
import com.davidredondo.entity.BillingShift;
import com.davidredondo.exception.ValidationException;
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
		Integer sessionDuration = calculatePortionSessionDuration(billingShift);
		billingPortion.setSession(sessionDuration);
		billingPortion.setPay(getPayBasedOnSessionDurationAndPayRate(sessionDuration));
		billingPortion.setId(this.getId());
		billingPortion.setStart(getWhenRuleStartAppliedForShift(billingShift));
		billingPortion.setEnd(getWhenRuleEndAppliedForShift(billingShift, sessionDuration));
		return billingPortion;
	}

	private double getPayBasedOnSessionDurationAndPayRate(Integer sessionDuration) {
		return getSessionDurationInHours(sessionDuration) * this.getPayRate();
	}

	private String getWhenRuleEndAppliedForShift(BillingShift billingShift, Integer sessionDuration) {
		return DateUtils.getJsonDateFormatFromDateTime(
				billingShift.getStartDateTime().plus(Seconds.seconds(start)).plus(Seconds.seconds(sessionDuration)));
	}

	private String getWhenRuleStartAppliedForShift(BillingShift billingShift) {
		return DateUtils.getJsonDateFormatFromDateTime(billingShift.getStartDateTime().plus(Seconds.seconds(start)));
	}

	private Double getSessionDurationInHours(Integer sessionDuration) {
		return Double.valueOf(DateUtils.secondsToHours(sessionDuration));
	}

	private Integer calculatePortionSessionDuration(BillingShift shift) {
		Integer shiftDuration = shift.getSessionTime().getSeconds();
		if (start > shiftDuration) {
			return 0;
		} else if (end > shiftDuration) {
			return shiftDuration - start;
		} else if (end < shiftDuration) {
			return end - start;
		}
		return 0;
	}

	@Override
	public void validate() {
		ruleStartsBeforeEnds();
	}

	private void ruleStartsBeforeEnds() {
		if (start > end) {
			ValidationException.throwWithInvalidObjectAndMessage(this, "End must be higher than start");
		}
	}

	public boolean equals(Object obj) {
		if (obj instanceof DurationBillingRule) {
			DurationBillingRule durationBillingRule = DurationBillingRule.class.cast(obj);
			return super.equals(obj) && durationBillingRule.start.equals(this.start)
					&& durationBillingRule.end.equals(this.end);
		}
		return false;
	}

}
