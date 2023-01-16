package com.robmcarrier.budgetapp.controllers;

import com.robmcarrier.budgetapp.models.BudgetItem;
import com.robmcarrier.budgetapp.services.BudgetItemService;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@GraphQlTest(MainController.class)
class MainControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private BudgetItemService budgetItemService;

    @Test
    void budgetItems() {
        List<BudgetItem> budgetItems = new ArrayList<>();

        BudgetItem budgetItem1 = createTestBudgetItem();
        BudgetItem budgetItem2 = createTestBudgetItem();
        BudgetItem budgetItem3 = createTestBudgetItem();
        BudgetItem budgetItem4 = createTestBudgetItem();

        budgetItems.add(budgetItem1);
        budgetItems.add(budgetItem2);
        budgetItems.add(budgetItem3);
        budgetItems.add(budgetItem4);

        Flux<BudgetItem> budgetItemFlux = Flux.fromIterable(budgetItems);
        String document = "query {" +
                "  budgetItems {" +
                "   id" +
                "   name" +
                "   amount" +
                "   dayOfMonth" +
                "  }" +
                "}";

        given(budgetItemService.getBudgetItems()).willReturn(budgetItemFlux);

        List<BudgetItem> responseItems = graphQlTester.document(document)
                .execute()
                .path("budgetItems")
                .entityList(BudgetItem.class)
                .get();

        assertThat(responseItems.size()).isEqualTo(4);

        assertThat(budgetItem1.equals(responseItems.get(0))).isTrue();
        assertThat(budgetItem2.equals(responseItems.get(1))).isTrue();
        assertThat(budgetItem3.equals(responseItems.get(2))).isTrue();
        assertThat(budgetItem4.equals(responseItems.get(3))).isTrue();
    }

    @Test
    void createBudgetItem() {
        BudgetItem budgetItem = createTestBudgetItem();
        BudgetItem request = new BudgetItem();
        request.setName(budgetItem.getName());
        request.setDayOfMonth(budgetItem.getDayOfMonth());
        request.setSplit(budgetItem.isSplit());
        request.setAmount(budgetItem.getAmount());

        given(budgetItemService.createBudgetItem(request)).willReturn(Mono.just(budgetItem));

        String document = "mutation($name: String! $amount: Int $dayOfMonth: Int $split: Boolean) {" +
                "  createBudgetItem(budgetItem: {" +
                "   name: $name," +
                "   amount: $amount," +
                "   dayOfMonth: $dayOfMonth," +
                "   split: $split" +
                "   }) {" +
                "   id" +
                "   name" +
                "  }" +
                "}";

        BudgetItem responseItem = graphQlTester.document(document)
                .variable("name", budgetItem.getName())
                .variable("amount", budgetItem.getAmount())
                .variable("dayOfMonth", budgetItem.getDayOfMonth())
                .variable("split", budgetItem.isSplit())
                .execute()
                .path("createBudgetItem")
                .entity(BudgetItem.class)
                .get();

        assertThat(responseItem.getName()).isEqualTo(budgetItem.getName());
        assertThat(responseItem.getId()).isEqualTo(budgetItem.getId());
        assertThat(responseItem.getAmount()).isEqualTo(0);
    }

    @Test
    void deleteBudgetItem() {
        String id = "test";
        given(budgetItemService.deleteBudgetItem(id)).willReturn(Mono.empty());

        String document = "mutation($id: ID!) {" +
                "  deleteBudgetItem(id: $id) {" +
                "       id" +
                "       name" +
                "  }" +
                "}";

        graphQlTester.document(document)
                .variable("id", "test")
                .executeAndVerify();

        verify(budgetItemService).deleteBudgetItem(id);
    }

    private BudgetItem createTestBudgetItem() {
        Faker faker = new Faker();
        BudgetItem budgetItem = new BudgetItem();
        budgetItem.setId(faker.internet().uuid());
        budgetItem.setName(faker.appliance().brand());
        budgetItem.setAmount(new Random().nextInt(1000 - 10 + 1) + 10);
        budgetItem.setDayOfMonth(new Random().nextInt(31 - 1 + 1) + 1);
        budgetItem.setSplit(false);
        return budgetItem;
    }
}