package com.food.dataaccess.customer.mapper;

import com.food.dataaccess.customer.entity.CustomerEntity;
import com.food.order.domain.valueobject.CustomerId;
import com.food.order.service.domain.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {
    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}
