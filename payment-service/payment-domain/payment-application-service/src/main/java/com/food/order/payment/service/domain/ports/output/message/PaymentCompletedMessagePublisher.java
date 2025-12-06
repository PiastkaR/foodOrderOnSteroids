package com.food.order.payment.service.domain.ports.output.message;

import com.food.order.domain.event.publisher.DomainEventPublisher;
import com.food.order.service.domain.event.PaymentCompletedEvent;

public interface PaymentCompletedMessagePublisher extends DomainEventPublisher<PaymentCompletedEvent> {
}