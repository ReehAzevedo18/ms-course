package com.curso.hrpayroll.services;

import com.curso.hrpayroll.entities.Payment;
import com.curso.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String workerHost;

    @Autowired
    private RestTemplate restTemplate;

    public Payment getPayment(long workerID, int days){
    /*É necessário criar um mapa com os parametros que podem entrar na URL, nesse caso,
    será um mapa com os IDs dos trabalhadores*/
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("id", ""+workerID); //fazendo o mapa de parametros que vão entrar na URL

    /*  Para utilizar o restTemplate é necessário fazer uma cópia da entidade que está na outra API*/
    Worker worker = restTemplate.getForObject
            (workerHost + "/workers/{id}", Worker.class, uriVariables); //eu passo a URL, a classe e os parametros que serão passados
    return new Payment(worker.getName(),worker.getDailyInCome(),days);
    }
}

