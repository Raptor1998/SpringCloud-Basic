package com.raptor.service.impl;

import com.raptor.dao.PaymentDao;
import com.raptor.entity.domain.Payment;
import com.raptor.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author raptor
 * @description PaymentServiceImpl
 * @date 2021/8/27 9:37
 */
@Service
public class PaymentServiceImpl implements PaymentService {


    private PaymentDao paymentDao;

    @Autowired
    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public Integer create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
