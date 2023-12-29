package com.example.EPM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewResponse {
    private double Amount;
    private double installment;
    private double profit;
    private double principalAmount;
    private String message;
    public NewResponse(Integer Amount,double installment,double profit,double principalAmount){
        this.installment = installment;
        this.Amount = Amount;
        this.principalAmount = principalAmount;
        this.profit =profit;
    }
    public NewResponse(Integer Amount, String message){
        this.message =message;
        this.Amount = Amount;

    }
}
