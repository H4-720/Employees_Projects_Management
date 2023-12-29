package com.example.EPM.controller;

import com.example.EPM.bean.LoanCalculator;
import com.example.EPM.model.NewResponse;
import com.example.EPM.model.ResponseView;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/calculate")
public class LoanCalculatorController {
    @PostMapping("/loanCalculator")
    public ResponseView calculate(@RequestBody LoanCalculator loanCalculator) {
        List<NewResponse> lr = new ArrayList<>();

        if (loanCalculator.getRequestedTenure() % loanCalculator.getInstallmentPeriod() != 0) {
            lr.add(new NewResponse(0, "Requested Tenure is not valid input"));
            return new ResponseView(lr, 0, 0);
        }

        int n = loanCalculator.getRequestedTenure() / loanCalculator.getInstallmentPeriod();
        double pv = loanCalculator.getAmount();
        double R = (loanCalculator.getMarkUprate() / 100.0) / loanCalculator.getInstallmentPeriod();

        double pmt = (pv * R) / (1 - Math.pow(1 + R, -n));

        double currentAmount = pv;
        double sumOfAmount = 0;
        double sumOfProfit = 0;

        for (int i = 0; i < n; i++) {
            double interest = currentAmount * R;
            sumOfProfit += interest;
            double principal = pmt - interest;
            sumOfAmount += principal;
            currentAmount -= principal;

            lr.add(new NewResponse((int) currentAmount, pmt, interest, principal));
        }

        return new ResponseView(lr, sumOfProfit, sumOfAmount);
    }
}

