package com.example.paymentservice.service;

import com.example.paymentservice.model.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(Payment payment) {
        payment.setStatus("PENDING"); // Set initial status
        // Payment processing logic here
        payment.setStatus("SUCCESS"); // Update status after processing
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(String id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment findByCustomerId(String customerId) {
        log.info("Searching payment for customer ID: {}", customerId);

        Payment payment = paymentRepository.findByCustomerId(customerId);

        if (payment != null) {
            log.info("Payment found for customer ID: {}", customerId);
        } else {
            log.warn("No payment found for customer ID: {}", customerId);
        }

        return payment;
    }
}
