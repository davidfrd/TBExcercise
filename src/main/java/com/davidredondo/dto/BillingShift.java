package com.davidredondo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

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
	public boolean validate() {
		return sessionTimeLessThan(MAX_SESSION_IN_SECONDS) && sessionStartsBeforeEnds();
	}
	
	private boolean sessionStartsBeforeEnds() {
		return getStartDateTime().isBefore(getEndDateTime());
	}
	
	private boolean sessionTimeLessThan(Integer seconds) {
		return getSessionTime().isLessThan(Seconds.seconds(seconds));
	}

}
