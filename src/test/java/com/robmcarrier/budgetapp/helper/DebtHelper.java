package com.robmcarrier.budgetapp.helper;

import com.robmcarrier.budgetapp.models.Debt;
import net.datafaker.Faker;

import java.util.Random;

public class DebtHelper {
    public static Debt createTestDebt() {
        Faker faker = new Faker();
        Debt debt = new Debt();
        debt.setId(faker.internet().uuid());
        debt.setName(faker.appliance().brand());
        debt.setAmount(new Random().nextInt(1000 - 10 + 1) + 10);
        debt.setInterestRate("10");
        return debt;
    }
}
