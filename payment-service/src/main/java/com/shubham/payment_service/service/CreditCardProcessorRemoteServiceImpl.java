package com.shubham.payment_service.service;

import com.shubham.core.dto.CreditCardProcessRequest;
import com.shubham.core.exception.CreditCardProcessedUnavilableException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CreditCardProcessorRemoteServiceImpl implements CreditCardProcessorRemoteService{

    private final RestTemplate restTemplate;
    private final String ccpRemoteServiceUrl;

    public CreditCardProcessorRemoteServiceImpl(
            RestTemplate restTemplate,
            @Value("${remote.ccp.url}") String ccpRemoteServiceUrl
    ){
        this.restTemplate=restTemplate;
        this.ccpRemoteServiceUrl= ccpRemoteServiceUrl;
    }

    @Override
    public void process(BigInteger cardNumber, BigDecimal paymentAmount) {
        try{
            var request = new CreditCardProcessRequest(cardNumber,paymentAmount);
            restTemplate.postForObject(ccpRemoteServiceUrl + "/ccp/process",request,CreditCardProcessRequest.class);
        }catch (ResourceAccessException e){
            throw new CreditCardProcessedUnavilableException(e);
        }

    }
}
