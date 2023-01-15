package com.robmcarrier.budgetapp.controllers;

import com.robmcarrier.budgetapp.models.BudgetItem;
import com.robmcarrier.budgetapp.services.BudgetItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@GraphQlTest(MainController.class)
class MainControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private BudgetItemService budgetItemService;

    @Test
    void budgetItems() {
        List<BudgetItem> budgetItems = new ArrayList<>();
        BudgetItem budgetItem1 = new BudgetItem();
        budgetItem1.setId("12345");
        budgetItem1.setName("test1");
        budgetItem1.setAmount(100);
        budgetItem1.setDayOfMonth(11);
        budgetItems.add(budgetItem1);

        Flux<BudgetItem> budgetItemFlux = Flux.fromIterable(budgetItems);
        String document = "query {" +
                "  budgetItems {" +
                "   id" +
                "   name" +
                "  }" +
                "}";

        given(budgetItemService.getBudgetItems()).willReturn(budgetItemFlux);

        graphQlTester.document(document)
                .execute()
                .path("budgetItems[0]")
                .entity(BudgetItem.class)
                .equals(budgetItem1);
    }

    @Test
    void createBudgetItem() {
    }

    @Test
    void deleteBudgetItem() {
    }
}