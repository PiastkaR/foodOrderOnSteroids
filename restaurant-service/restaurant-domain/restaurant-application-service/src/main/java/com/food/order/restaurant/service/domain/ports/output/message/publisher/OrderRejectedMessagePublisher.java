package com.food.order.restaurant.service.domain.ports.output.message.publisher;

import com.food.order.domain.event.publisher.DomainEventPublisher;
import com.food.order.restaurant.service.domain.event.OrderRejectedEvent;

public interface OrderRejectedMessagePublisher extends DomainEventPublisher<OrderRejectedEvent> {
}