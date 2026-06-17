package com.shubham.credit_card_processor_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ccp")
public class CreditCardController {

    private static final Logger LOGGER= LoggerFactory.getLogger(CreditCardController.class);

    @PostMapping("/process")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void processCreditCard(@RequestBodyrequest @Valid CreditCardProcessRequest request){
        LOGGER.info("Processing request : {}",request);
    }

}
