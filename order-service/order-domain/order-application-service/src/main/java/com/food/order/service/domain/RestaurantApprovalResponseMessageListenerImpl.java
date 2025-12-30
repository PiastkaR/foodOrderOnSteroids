package com.food.order.service.domain;

import com.food.order.service.domain.dto.message.RestaurantApprovalResponse;
import com.food.order.service.domain.event.OrderCancelledEvent;
import com.food.order.service.domain.ports.input.message.listener.restaurantapproval.RestaurantApprovalResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {

    private final OrderApprovalSaga orderApprovalSaga;

    public RestaurantApprovalResponseMessageListenerImpl(OrderApprovalSaga orderApprovalSaga) {
        this.orderApprovalSaga = orderApprovalSaga;
    }

    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {
        orderApprovalSaga.process(restaurantApprovalResponse);
        log.info("Order with Id {} approved successfully", restaurantApprovalResponse.getOrderId());
    }

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {
        OrderCancelledEvent cancelledEvent = orderApprovalSaga.rollback(restaurantApprovalResponse);
        log.info("Order with Id {} rejected successfully", restaurantApprovalResponse.getOrderId());
        cancelledEvent.fire();
    }
}
