package com.robmcarrier.budgetapp.controllers;

import com.robmcarrier.budgetapp.models.BudgetItem;
import com.robmcarrier.budgetapp.services.BudgetItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final BudgetItemService budgetItemService;

    @GetMapping(path = "/items")
    public Flux<BudgetItem> getBudgetItems() {
        return budgetItemService.getBudgetItems();
    }

    @PostMapping(path = "/items")
    public Mono<BudgetItem> createBudgetItem(BudgetItem budgetItem) {
        return budgetItemService.createBudgetItem(budgetItem);
    }

    @DeleteMapping(path = "/item/{name}")
    public Mono<Void> deleteBudgetItem(@PathVariable("name") String name) {
        return budgetItemService.deleteBudgetItem(name);
    }
}
