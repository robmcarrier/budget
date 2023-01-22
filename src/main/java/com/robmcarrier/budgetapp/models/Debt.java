package com.robmcarrier.budgetapp.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
public class Debt {
    @Id
    private String id;
    private String name;
    private int amount;
    private String interestRate;
}
