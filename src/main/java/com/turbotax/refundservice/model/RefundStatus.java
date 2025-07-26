package com.turbotax.refundservice.model;

import java.time.LocalDate;

public class RefundStatus {
    private boolean available;
    private String expectedDate;

    public RefundStatus(boolean available, String expectedDate) {
        this.available = available;
        this.expectedDate = expectedDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getExpectedDate() {
        return expectedDate;
    }
}
