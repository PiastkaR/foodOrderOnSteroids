package com.food.order.restaurant.service.domain.ports.output.repository;

import com.food.order.restaurant.service.domain.entity.OrderApproval;

public interface OrderApprovalRepository {
    OrderApproval save(OrderApproval orderApproval);
}