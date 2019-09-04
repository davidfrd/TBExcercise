package com.davidredondo.service.interfaces;

import com.davidredondo.dto.request.BillingRequest;
import com.davidredondo.dto.response.BillingResponse;

public interface IBillingService {

	public BillingResponse calculateBilling(BillingRequest billingRequest);
}
