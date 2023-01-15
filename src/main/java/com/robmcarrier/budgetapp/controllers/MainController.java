package com.robmcarrier.budgetapp.controllers;

import com.robmcarrier.budgetapp.models.BudgetItem;
import com.robmcarrier.budgetapp.services.BudgetItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final BudgetItemService budgetItemService;

    @QueryMapping
    public Flux<BudgetItem> budgetItems() {
        return budgetItemService.getBudgetItems();
    }

    @MutationMapping
    public Mono<BudgetItem> createBudgetItem(@Argument BudgetItem budgetItem) {
        return budgetItemService.createBudgetItem(budgetItem);
    }

    @MutationMapping
    public Mono<Void> deleteBudgetItem(@Argument String name) {
        return budgetItemService.deleteBudgetItem(name);
    }
}
