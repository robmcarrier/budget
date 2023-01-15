package com.robmcarrier.budgetapp.repositories;

import com.robmcarrier.budgetapp.models.BudgetItem;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BudgetItemRepository extends ReactiveMongoRepository<BudgetItem, String> {
}
