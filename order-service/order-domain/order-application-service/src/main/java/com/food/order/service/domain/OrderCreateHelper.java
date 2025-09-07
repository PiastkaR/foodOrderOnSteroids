package com.food.order.service.domain;

import com.food.order.service.domain.dto.create.CreateOrderCommand;
import com.food.order.service.domain.entity.Customer;
import com.food.order.service.domain.entity.Order;
import com.food.order.service.domain.entity.Restaurant;
import com.food.order.service.domain.event.OrderCreatedEvent;
import com.food.order.service.domain.exception.OrderDomainException;
import com.food.order.service.domain.maper.OrderDataMapper;
import com.food.order.service.domain.ports.output.repository.CustomerRepository;
import com.food.order.service.domain.ports.output.repository.OrderRepository;
import com.food.order.service.domain.ports.output.repository.RestaurantRepository;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateHelper {
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;

    public OrderCreateHelper(
            OrderDomainService orderDomainService,
            OrderRepository orderRepository,
            CustomerRepository customerRepository,
            RestaurantRepository restaurantRepository,
            OrderDataMapper orderDataMapper
    ) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        saveOrder(order);
        log.info("Order with id {} created successfully", order.getId().getValue());

        return orderCreatedEvent;
    }

    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant rest = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(rest);
        if (optionalRestaurant.isEmpty()) {
            log.warn("Restaurant with id {} not found", createOrderCommand.getRestaurantId());
            throw new OrderDomainException("Restaurant with id " + createOrderCommand.getRestaurantId() + " not found");
        }
        return optionalRestaurant.get();
    }

    private void checkCustomer(@NotNull UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if (customer.isEmpty()) {
            log.warn("Customer with id {} not found", customerId);
            throw new OrderDomainException("Customer with id " + customerId + " not found");
        }
    }

    private Order saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        if (savedOrder == null) {
            log.error("Could not save order");
            throw new OrderDomainException("Could not save order");
        }
        log.info("Order with id {} saved successfully", savedOrder.getId().getValue());
        return savedOrder;
    }
}
