package com.example.EPM.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoanCalculator {
    private Integer amount;
    private Integer installmentPeriod;
    private Integer requestedTenure;
    private Integer markUprate;
}
