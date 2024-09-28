package com.spring.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

	public void makePayment() {
		System.out.println("Payment has been made.");
	}

}
