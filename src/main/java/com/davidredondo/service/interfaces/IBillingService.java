package com.davidredondo.service.interfaces;

import com.davidredondo.entity.request.BillingRequest;
import com.davidredondo.entity.response.BillingResponse;

public interface IBillingService {

	public BillingResponse calculateBilling(BillingRequest billingRequest);
}
