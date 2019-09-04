package com.davidredondo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidredondo.dto.request.BillingRequest;
import com.davidredondo.dto.response.BillingResponse;
import com.davidredondo.service.interfaces.IBillingService;

@RestController
@RequestMapping("/billing")
public class BillingController {
	
	@Autowired
	IBillingService billingService;

	
	@PostMapping
	public BillingResponse calculateBilling(@RequestBody BillingRequest billingRequest) {
		return billingService.calculateBilling(billingRequest);
	}
}
