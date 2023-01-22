package com.robmcarrier.budgetapp.services;

import com.robmcarrier.budgetapp.models.Debt;
import com.robmcarrier.budgetapp.repositories.DebtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Component
public class DebtService {

    private final DebtRepository debtRepository;

    public Flux<Debt> getAllDebt() {
        return debtRepository.findAll();
    }

}
