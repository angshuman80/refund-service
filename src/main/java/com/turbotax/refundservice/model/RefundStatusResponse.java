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

    public Integer getLast4ssn() {
        return last4ssn;
    }

    public void setLast4ssn(Integer last4ssn) {
        this.last4ssn = last4ssn;
    }

    public Integer getTaxReturnYear() {
        return taxReturnYear;
    }

    public void setTaxReturnYear(Integer taxReturnYear) {
        this.taxReturnYear = taxReturnYear;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Double getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(Double returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getFillingType() {
        return fillingType;
    }

    public void setFillingType(String fillingType) {
        this.fillingType = fillingType;
    }

    public String getDisputeReason() {
        return disputeReason;
    }

    public void setDisputeReason(String disputeReason) {
        this.disputeReason = disputeReason;
    }

    public String getTaxRefundDate() {
        return taxRefundDate;
    }

    public void setTaxRefundDate(String taxRefundDate) {
        this.taxRefundDate = taxRefundDate;
    }

    public String getExpectedDepositDate() {
        return expectedDepositDate;
    }

    public void setExpectedDepositDate(String expectedDepositDate) {
        this.expectedDepositDate = expectedDepositDate;
    }
}
