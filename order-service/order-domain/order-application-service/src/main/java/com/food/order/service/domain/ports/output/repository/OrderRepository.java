package com.food.order.service.domain.ports.output.repository;

import com.food.order.domain.valueobject.OrderId;
import com.food.order.service.domain.entity.Order;
import com.food.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(OrderId orderId);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
