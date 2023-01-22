package com.robmcarrier.budgetapp.repositories;

import com.robmcarrier.budgetapp.models.Debt;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DebtRepository extends ReactiveMongoRepository<Debt, String> {
}
