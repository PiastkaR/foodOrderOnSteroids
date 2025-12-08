package com.food.order.restaurant.service.domain.mapping;

import com.food.order.domain.valueobject.Money;
import com.food.order.domain.valueobject.OrderId;
import com.food.order.domain.valueobject.OrderStatus;
import com.food.order.domain.valueobject.RestaurantId;
import com.food.order.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.food.order.restaurant.service.domain.entity.OrderDetail;
import com.food.order.restaurant.service.domain.entity.Product;
import com.food.order.restaurant.service.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataMapper {
    public Restaurant restaurantApprovalRequestToRestaurant(RestaurantApprovalRequest
                                                                             restaurantApprovalRequest) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(UUID.fromString(restaurantApprovalRequest.getRestaurantId())))
                .orderDetail(OrderDetail.builder()
                        .orderId(new OrderId(UUID.fromString(restaurantApprovalRequest.getOrderId())))
                        .products(restaurantApprovalRequest.getProducts().stream().map(
                                product -> Product.builder()
                                        .productId(product.getId())
                                        .quantity(product.getQuantity())
                                        .build())
                                .collect(Collectors.toList()))
                        .totalAmount(new Money(restaurantApprovalRequest.getPrice()))
                        .orderStatus(OrderStatus.valueOf(restaurantApprovalRequest.getRestaurantOrderStatus().name()))
                        .build())
                .build();
    }
}