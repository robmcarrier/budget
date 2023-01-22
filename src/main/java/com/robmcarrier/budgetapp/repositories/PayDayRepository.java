package com.robmcarrier.budgetapp.repositories;

import com.robmcarrier.budgetapp.models.PayDay;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PayDayRepository extends ReactiveMongoRepository<PayDay, String> {
}
