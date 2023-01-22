package com.robmcarrier.budgetapp.controllers;

import com.robmcarrier.budgetapp.models.BudgetItem;
import com.robmcarrier.budgetapp.models.Debt;
import com.robmcarrier.budgetapp.services.BudgetItemService;
import com.robmcarrier.budgetapp.services.DebtService;
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
    private final DebtService debtService;

    @QueryMapping
    public Flux<BudgetItem> budgetItems() {
        return budgetItemService.getBudgetItems();
    }

    @QueryMapping
    public Flux<Debt> getAllDebt() {
        return debtService.getAllDebt();
    }

    @MutationMapping
    public Mono<BudgetItem> createBudgetItem(@Argument BudgetItem budgetItem) {
        return budgetItemService.createBudgetItem(budgetItem);
    }

    @MutationMapping
    public Mono<Void> deleteBudgetItem(@Argument String id) {
        return budgetItemService.deleteBudgetItem(id);
    }
}
