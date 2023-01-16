package com.robmcarrier.budgetapp.services;

import com.robmcarrier.budgetapp.models.BudgetItem;
import com.robmcarrier.budgetapp.repositories.BudgetItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.robmcarrier.budgetapp.helper.BudgetItemHelper.createTestBudgetItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

        Iterator<BudgetItem> actual = budgetItemService.getBudgetItems().toIterable().iterator();
        int idx = 0;
        while(actual.hasNext()) {
            assertThat(actual.next()).isEqualTo(budgetItems.get(idx));
            idx++;
        }

        verify(budgetItemRepository).findAll();
    }

    @Test
    void createBudgetItem() {
        BudgetItem newBudgetItem = createTestBudgetItem();
        given(budgetItemRepository.save(newBudgetItem)).willReturn(Mono.just(newBudgetItem));

        Mono<BudgetItem> actual = budgetItemService.createBudgetItem(newBudgetItem);

        assertThat(actual.block()).isEqualTo(newBudgetItem);
    }

    @Test
    void deleteBudgetItem() {
        String id = "test";
        given(budgetItemRepository.deleteById(id)).willReturn(Mono.empty());

        budgetItemService.deleteBudgetItem(id);

        verify(budgetItemRepository).deleteById(id);
    }
}