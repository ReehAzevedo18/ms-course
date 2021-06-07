package com.curso.hrpayroll.resources;

import com.curso.hrpayroll.entities.Payment;
import com.curso.hrpayroll.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

    @Autowired
    private PaymentService service;

    @GetMapping(value = "/{workerID}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerID, @PathVariable Integer days){
        Payment pay = service.getPayment(workerID, days);
        return ResponseEntity.ok(pay);
    }
}
