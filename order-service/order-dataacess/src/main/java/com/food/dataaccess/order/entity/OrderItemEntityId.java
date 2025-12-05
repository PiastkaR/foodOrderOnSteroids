package com.food.dataaccess.order.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor//required for builder
@AllArgsConstructor//required for proxy
//@IdClass(OrderItemEntityId.class)//required for id class with multi-column primary key
public class OrderItemEntityId implements Serializable {

    private Long id;
    private OrderEntity order;

    //equals and hashcode for unique id fields!
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntityId that = (OrderItemEntityId) o;
        return Objects.equals(id, that.id) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }
}
