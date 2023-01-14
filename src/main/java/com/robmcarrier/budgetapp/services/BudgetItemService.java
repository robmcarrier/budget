package com.robmcarrier.budgetapp.services;

import com.robmcarrier.budgetapp.models.BudgetItem;
import com.robmcarrier.budgetapp.repositories.BudgetItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class BudgetItemService {

    private final BudgetItemRepository budgetItemRepository;

    public Flux<BudgetItem> getBudgetItems() {
        return budgetItemRepository.findAll();
    }

    public Mono<BudgetItem> createBudgetItem(BudgetItem budgetItem) {
        return budgetItemRepository.save(budgetItem);
    }

    public Mono<Void> deleteBudgetItem(String name) {
        return budgetItemRepository.deleteByName(name);
    }
}
