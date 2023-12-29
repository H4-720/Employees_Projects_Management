package com.example.EPM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseView {
    List<NewResponse> responseViewList;
    private double totalProfit;
    private double totalAmount;
}
