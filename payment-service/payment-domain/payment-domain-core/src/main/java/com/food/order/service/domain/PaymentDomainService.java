package com.food.order.service.domain;

import com.food.order.domain.event.publisher.DomainEventPublisher;
import com.food.order.service.domain.entity.CreditEntry;
import com.food.order.service.domain.entity.CreditHistory;
import com.food.order.service.domain.entity.Payment;
import com.food.order.service.domain.event.PaymentCancelledEvent;
import com.food.order.service.domain.event.PaymentCompletedEvent;
import com.food.order.service.domain.event.PaymentEvent;
import com.food.order.service.domain.event.PaymentFailedEvent;

import java.util.List;

public interface PaymentDomainService {
    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages,
                                            DomainEventPublisher<PaymentCompletedEvent>
                                                    paymentCompletedEventDomainEventPublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessages, DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);
}
