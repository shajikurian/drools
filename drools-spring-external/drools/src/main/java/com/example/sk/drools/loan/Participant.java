package com.example.sk.drools.loan;

import lombok.Data;

/**
 * Created on 10/12/2024
 * {@code @authors} shaji
 */
@Data
public class Participant {
    private String name;
    private int age;
    private int creditScore;
    private long annualSalary;
    private long existingDebt;
    private long loanAmount;
}
