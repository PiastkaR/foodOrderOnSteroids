package com.food.order.payment.service.domain.mapper;

import com.food.order.domain.valueobject.CustomerId;
import com.food.order.domain.valueobject.Money;
import com.food.order.domain.valueobject.OrderId;
import com.food.order.payment.service.domain.dto.PaymentRequest;
import com.food.order.service.domain.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentDataMapper {

    public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderId(new OrderId(UUID.fromString(paymentRequest.getOrderId())))
                .customerId(new CustomerId(UUID.fromString(paymentRequest.getCustomerId())))
                .price(new Money(paymentRequest.getPrice()))
                .build();
    }
}