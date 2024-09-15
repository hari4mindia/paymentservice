package com.example.paymentservice.service;

import com.example.paymentservice.model.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
}
