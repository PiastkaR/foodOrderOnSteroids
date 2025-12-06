package com.food.order.payment.service.domain.ports.output.message;

import com.food.order.domain.event.publisher.DomainEventPublisher;
import com.food.order.service.domain.event.PaymentCancelledEvent;

public interface PaymentCancelledMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {
}