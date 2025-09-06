package com.food.order.service.domain.valueobject;

import com.food.order.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
