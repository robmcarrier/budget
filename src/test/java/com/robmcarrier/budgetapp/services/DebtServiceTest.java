package com.robmcarrier.budgetapp.services;

import com.robmcarrier.budgetapp.models.Debt;
import com.robmcarrier.budgetapp.repositories.DebtRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DebtServiceTest {

    @Mock
    private DebtRepository debtRepository;

    @InjectMocks
    private DebtService debtService;

    @Test
    void getAllDebt() {
        List<Debt> debtList = Instancio.ofList(Debt.class).size(3).create();

        given(debtRepository.findAll()).willReturn(Flux.fromIterable(debtList));

        Iterator<Debt> actual = debtService.getAllDebt().toIterable().iterator();
        int idx = 0;
        while (actual.hasNext()) {
            assertThat(actual.next()).isEqualTo(debtList.get(idx));
            idx++;
        }

        verify(debtRepository).findAll();
    }
}