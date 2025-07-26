package com.turbotax.refundservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Setter
public class RefundStatusResponse {
    private Integer last4ssn;
    private Integer taxReturnYear;
    private String refundStatus;
    private Double returnAmount;
    private String fillingType;
    private String disputeReason;
    private String taxRefundDate;
    private String expectedDepositDate;


}
