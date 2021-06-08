package com.curso.hrpayroll.resources;

import com.curso.hrpayroll.entities.Payment;
import com.curso.hrpayroll.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(fallbackMethod = "getPaymentAlternative") //ele chama o método alternativo, caso tenha alguma falha ao realizar a comunicação com o outro microsserviço (hr-worker)
    @GetMapping(value = "/{workerID}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerID, @PathVariable Integer days){
        Payment pay = service.getPayment(workerID, days);
        return ResponseEntity.ok(pay);
    }

    //Método alternativo para falhas
    public ResponseEntity<Payment> getPaymentAlternative(Long workerID, Integer days){
        Payment pay = new Payment("Brann", 400.0, days);
        return ResponseEntity.ok(pay);
    }
}
