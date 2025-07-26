package com.turbotax.refundservice.model.table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaxReturn {

    private String userId;
    private int year;
    private String ssn;
    private int last4;
    private double refundAmount;
    private String refundStatus;
    private String fillingDate;
    private String refundDate;

    // Getters and Setters
}