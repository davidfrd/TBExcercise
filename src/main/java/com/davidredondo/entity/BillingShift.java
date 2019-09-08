package com.davidredondo.entity;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import com.davidredondo.exception.ValidationException;
import com.davidredondo.util.DateUtils;
import com.davidredondo.util.interfaces.Validable;

public class BillingShift implements Validable, Serializable {

	private static final long serialVersionUID = -3378185789225546172L;

	private static final int MAX_SESSION_IN_SECONDS = 24 * 3600;
	
	@NotNull
	@Positive
	private Integer id;
	
	@NotNull
	@NotEmpty
	private String start;
	
	@NotNull
	@NotEmpty
	private String end;

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
	
	public DateTime getStartDateTime() {
		return DateUtils.getDateTimeFromJsonDateFormat(start);
	}
	
	public DateTime getEndDateTime() {
		return DateUtils.getDateTimeFromJsonDateFormat(end);
	}
	
	public Integer getSessionInSeconds() {
		return getSessionTime().getSeconds();
	}
	
	public Seconds getSessionTime() {
		return Seconds.secondsBetween(getStartDateTime(), getEndDateTime());
	}

	@Override
	public void validate() {
		sessionTimeLessThan(MAX_SESSION_IN_SECONDS); 
		sessionStartsBeforeEnds();
	}
	
	private void sessionStartsBeforeEnds() {
		if (getStartDateTime().isAfter(getEndDateTime())) {
			ValidationException.throwWithInvalidObjectAndMessage(this, "Session ends before starts");
		}
	}
	
	private void sessionTimeLessThan(Integer seconds) {
		if (getSessionTime().isGreaterThan(Seconds.seconds(seconds))) {
			ValidationException.throwWithInvalidObjectAndMessage(this, "Session time is higher than " + seconds);
		};
	}

	public boolean equals(Object obj) {
		if (obj instanceof BillingShift) {
			BillingShift billingShift = BillingShift.class.cast(obj);
			return billingShift.id.equals(this.id) && billingShift.start.equals(this.start) && billingShift.end.equals(this.end);
		}
		return false;
	}
}
