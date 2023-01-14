package com.robmcarrier.budgetapp.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document("budget_items")
public class BudgetItem {
    @Id
    private String id;
    private String name;
    private int amount;
    private int dayOfMonth;
    private boolean split;
}
