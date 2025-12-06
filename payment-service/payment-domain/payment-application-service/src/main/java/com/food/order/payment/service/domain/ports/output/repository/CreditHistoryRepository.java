package com.food.order.payment.service.domain.ports.output.repository;

import com.food.order.domain.valueobject.CustomerId;
import com.food.order.service.domain.entity.CreditHistory;

import java.util.List;
import java.util.Optional;

public interface CreditHistoryRepository {

    CreditHistory save(CreditHistory creditHistory);

    Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}