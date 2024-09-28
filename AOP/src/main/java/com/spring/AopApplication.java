package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.service.PaymentService;
import com.spring.service.PaymentServiceImpl;

@SpringBootApplication
public class AopApplication  implements CommandLineRunner{
    @Autowired
	private PaymentService paymentService;
	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
		System.out.println("Praveen");
		/*
		 * PaymentServiceImpl impl=new PaymentServiceImpl(); impl.makePayment();
		 */
		

	}
	@Override
    public void run(String... args) {
        paymentService.makePayment();
    }

}
/*
 * 1.Aop is a programming paradgm that aim to increase modularity by allowing the separation of cross cutting concern
 * 
 * 2.Process of applying services or external services as transaxtion management or logging to our application classes
 * without modifying the code.
 * 
 * 3.This services also called cross cutting concern
 * 
 * 4. AOP complements OOP by providing another way of thinking about program structure.
 * 
 * Aop , aspect, advice,Joinpoint, Pintcut
 * 
 * Aspect: An aspect is a modularization of a concern that cuts across multiple classes.
 * Join Point: Join point is any point in your program such as method exection point
 * 
 * Advice: Advice represent an action taken by an aspect at particular joint point.
 * 
 * Pointcut it is an expresstion of Aop that marches join point.
 * 
 * */
 