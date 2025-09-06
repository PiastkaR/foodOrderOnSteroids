package com.food.order.service.domain.entity;

import com.food.order.domain.entity.BaseEntity;
import com.food.order.domain.valueobject.Money;
import com.food.order.domain.valueobject.OrderId;
import com.food.order.service.domain.valueobject.OrderItemId;

import java.util.UUID;

public class OrderItem extends BaseEntity<OrderItemId> {
    private OrderId orderId;
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subTotal;

    private OrderItem(Builder builder) {
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
        product = builder.product;
        super.setId(builder.orderItemId);
    }

    public static Builder builder() { //dont use lombok in order to get rid of dependencies from domain core!!!
       return new Builder();
    }

    boolean isPriceValid() {
        return price.isGreaterThanZero() && price.equals(product.getPrice()) && price.multiply(quantity).equals(subTotal);
    }

    public OrderId getOrderId() {
        return orderId;
    }
    public UUID getProductId(){
        return product.getId().getValue();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    public static final class Builder {
        private int quantity;
        private Money price;
        private Money subTotal;
        private Product product;
        private OrderItemId orderItemId;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder id(OrderItemId val) {
            orderItemId = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
