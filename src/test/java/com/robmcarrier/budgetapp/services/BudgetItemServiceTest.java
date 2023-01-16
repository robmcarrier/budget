package com.robmcarrier.budgetapp.services;

import com.robmcarrier.budgetapp.models.BudgetItem;
import com.robmcarrier.budgetapp.repositories.BudgetItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static com.robmcarrier.budgetapp.helper.BudgetItemHelper.createTestBudgetItem;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BudgetItemServiceTest {

    @Mock
    private BudgetItemRepository budgetItemRepository;

    @InjectMocks
    private BudgetItemService budgetItemService;


    @Test
    void getBudgetItems() {
        List<BudgetItem> budgetItems = new ArrayList<>();
        BudgetItem budgetItem1 = createTestBudgetItem();
        BudgetItem budgetItem2 = createTestBudgetItem();
        BudgetItem budgetItem3 = createTestBudgetItem();
        BudgetItem budgetItem4 = createTestBudgetItem();
        budgetItems.add(budgetItem1);
        budgetItems.add(budgetItem2);
        budgetItems.add(budgetItem3);
        budgetItems.add(budgetItem4);

        given(budgetItemRepository.findAll()).willReturn(Flux.fromIterable(budgetItems));

        Flux<BudgetItem> actual = budgetItemService.getBudgetItems();
        System.out.println();
    }

    @Test
    void createBudgetItem() {
    }

    @Test
    void deleteBudgetItem() {
    }
}