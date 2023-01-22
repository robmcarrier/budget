package com.robmcarrier.budgetapp.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
public class PayDay {
    @Id
    private String id;
    private int amount;
    private boolean sameDay;
    private int startDate;
    private int startMonth;
    private int weeksBetweenPay;
}
