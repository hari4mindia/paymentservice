package com.example.paymentservice.controller;

import com.example.paymentservice.model.Payment;
import com.example.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment createdPayment = paymentService.processPayment(payment);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable String id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Payment> getPaymentsByCustomerId(@PathVariable String customerId) {
        log.info("Fetching payments for customer ID: {}", customerId);

        Payment payment =  paymentService.findByCustomerId(customerId);
        log.info("Payment  {}", payment);

        if (payment != null) {
            log.info("Payment found for customer ID: {}", customerId);
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            log.info("No payment found for customer ID: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
