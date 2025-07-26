package com.turbotax.refundservice.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RefundStatusPredictionService {

    public Integer predictRefundDays() {
        // code to return days between 10 to 22
        // This method simulates a prediction of refund days
        // It will replaced by calling real AI model in future

        return getRandomNumber();
    }
    private Integer getRandomNumber() {
        // Generate a random number between 10 and 22 (inclusive)
        return ThreadLocalRandom.current().nextInt(10, 23);
    }

}
