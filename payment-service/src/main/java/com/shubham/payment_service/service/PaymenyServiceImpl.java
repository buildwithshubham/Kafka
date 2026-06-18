package com.shubham.payment_service.service;

import com.shubham.core.dto.Payment;
import com.shubham.payment_service.PaymentRepository;
import com.shubham.payment_service.entity.PaymentEntity;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class PaymenyServiceImpl implements PaymentService {

    public static final String SAMPLE_CREDIT_CARD_NUMBER = "37424545454000126";
    private final PaymentRepository paymentRepository;
    private final CreditCardProcessorRemoteService ccpRemoteService;

    public PaymenyServiceImpl(PaymentRepository paymentRepository, CreditCardProcessorRemoteService ccpRemoteService) {
        this.paymentRepository = paymentRepository;
        this.ccpRemoteService = ccpRemoteService;
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll()
                .stream()
                .map(entity -> new Payment(entity.getId(),
                        entity.getOrderId(),
                        entity.getProductId(),
                        entity.getProductQuantity(),
                        entity.getProductPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Payment process(Payment payment) {
        BigDecimal totalPrice = payment.getProductPrice()
                .multiply(new BigDecimal(payment.getProductQuantity()));
        ccpRemoteService.process(new BigInteger(SAMPLE_CREDIT_CARD_NUMBER), totalPrice);
        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(payment, paymentEntity);
        paymentRepository.save(paymentEntity);

        var processedPayment = new Payment();
        BeanUtils.copyProperties(payment, processedPayment);
        processedPayment.setId(paymentEntity.getId());
        return processedPayment;
    }

}

