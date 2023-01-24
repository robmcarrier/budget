package com.robmcarrier.budgetapp.services;

import com.robmcarrier.budgetapp.models.Debt;
import com.robmcarrier.budgetapp.repositories.DebtRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.robmcarrier.budgetapp.helper.DebtHelper.createTestDebt;
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
        List<Debt> debtList = new ArrayList<>(3);
        Debt debt1 = createTestDebt();
        Debt debt2 = createTestDebt();
        Debt debt3 = createTestDebt();
        debtList.add(debt1);
        debtList.add(debt2);
        debtList.add(debt3);

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