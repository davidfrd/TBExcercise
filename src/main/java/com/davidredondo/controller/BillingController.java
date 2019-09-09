package com.davidredondo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidredondo.entity.request.BillingRequest;
import com.davidredondo.entity.response.BillingResponse;
import com.davidredondo.service.interfaces.IBillingService;

@RestController
@RequestMapping("/billing")
public class BillingController {

	@Autowired
	IBillingService billingService;

	@GetMapping
	public BillingResponse calculateBilling(@Valid @RequestBody BillingRequest billingRequest) {
		return billingService.calculateBilling(billingRequest);
	}
}
