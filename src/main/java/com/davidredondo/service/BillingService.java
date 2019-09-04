package com.davidredondo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.davidredondo.dto.BilledShift;
import com.davidredondo.dto.BillingPortion;
import com.davidredondo.dto.BillingShift;
import com.davidredondo.dto.request.BillingRequest;
import com.davidredondo.dto.response.BillingResponse;
import com.davidredondo.dto.rules.BillingRule;
import com.davidredondo.service.interfaces.IBillingService;

@Service
public class BillingService implements IBillingService {

	@Override
	public BillingResponse calculateBilling(BillingRequest billingRequest) {
		billingRequest.validate();
		return processBillingRequest(billingRequest);
	}

	private BillingResponse processBillingRequest(BillingRequest billingRequest) {
		List<BilledShift> billedShiftList = calculateAllBilledShift(billingRequest);
		return BillingResponse.from(billedShiftList);
	}

	private List<BilledShift> calculateAllBilledShift(BillingRequest billingRequest) {
		return billingRequest.getShifts().stream()
				.map(shift -> calculateBilledShift(shift, billingRequest.getRules()))
				.collect(Collectors.toList());
	}

	private BilledShift calculateBilledShift(BillingShift billingShift, List<BillingRule> billingRuleList) {
		List<BillingPortion> billingPortionList = calculateBillingPortionsShiftWithRules(billingShift, billingRuleList);
		return BilledShift.from(billingShift, billingPortionList);
	}

	private List<BillingPortion> calculateBillingPortionsShiftWithRules(BillingShift billingShift,
			List<BillingRule> billingRuleList) {
		return billingRuleList.stream()
				.map(rule -> rule.calculateBillingPortionFromShift(billingShift))
				.filter(billingPortion -> billingPortion.getSession() > 0)
				.collect(Collectors.toList());
	}

}
