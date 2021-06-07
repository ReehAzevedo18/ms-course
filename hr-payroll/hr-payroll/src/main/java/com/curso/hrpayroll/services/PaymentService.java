package com.curso.hrpayroll.services;

import com.curso.hrpayroll.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment getPayment(long workerID, int days){
        return new Payment("Bob",200.00,days);
    }
}

