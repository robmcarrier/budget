package com.robmcarrier.budgetapp.services;

import com.robmcarrier.budgetapp.models.BudgetItem;
import com.robmcarrier.budgetapp.repositories.BudgetItemRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Iterator;
import java.util.List;

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
        List<BudgetItem> budgetItems = Instancio.ofList(BudgetItem.class).size(4).create();

        given(budgetItemRepository.findAll()).willReturn(Flux.fromIterable(budgetItems));

        Iterator<BudgetItem> actual = budgetItemService.getBudgetItems().toIterable().iterator();
        int idx = 0;
        while (actual.hasNext()) {
            assertThat(actual.next()).isEqualTo(budgetItems.get(idx));
            idx++;
        }

        verify(budgetItemRepository).findAll();
    }

    @Test
    void createBudgetItem() {
        BudgetItem newBudgetItem = Instancio.create(BudgetItem.class);
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