package com.robmcarrier.budgetapp.helper;

import com.robmcarrier.budgetapp.models.BudgetItem;
import net.datafaker.Faker;

import java.util.Random;

public class BudgetItemHelper {

    public static BudgetItem createTestBudgetItem() {
        Faker faker = new Faker();
        BudgetItem budgetItem = new BudgetItem();
        budgetItem.setId(faker.internet().uuid());
        budgetItem.setName(faker.appliance().brand());
        budgetItem.setAmount(new Random().nextInt(1000 - 10 + 1) + 10);
        budgetItem.setDayOfMonth(new Random().nextInt(31 - 1 + 1) + 1);
        budgetItem.setSplit(new Random().nextBoolean());
        return budgetItem;
    }
}
