package com.food.order.service.domain;

import com.food.order.service.domain.dto.track.TrackOrderQuery;
import com.food.order.service.domain.dto.track.TrackOrderResponse;
import com.food.order.service.domain.entity.Order;
import com.food.order.service.domain.exception.OrderNotFoundException;
import com.food.order.service.domain.maper.OrderDataMapper;
import com.food.order.service.domain.ports.output.repository.OrderRepository;
import com.food.order.service.domain.valueobject.TrackingId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;

    public OrderTrackCommandHandler(OrderDataMapper orderDataMapper, OrderRepository orderRepository) {
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> optionalOrder = orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
        if (optionalOrder.isEmpty()) {
            log.warn("Order with tracking id {} not found", trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("Order with tracking id " + trackOrderQuery.getOrderTrackingId() + " not found");
        }

        return orderDataMapper.orderToTrackOrderResponse(optionalOrder.get());
    }


}
