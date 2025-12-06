package com.food.order.payment.service.domain.ports.output.repository;

import com.food.order.domain.valueobject.CustomerId;
import com.food.order.service.domain.entity.CreditEntry;

import java.util.Optional;

public interface CreditEntryRepository {

    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}