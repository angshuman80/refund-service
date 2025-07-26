package com.turbotax.refundservice.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String dateyyyymmdd(Integer addDays) {
        LocalDate addedDays = LocalDate.now().plusDays(addDays);
        String formattedDate = addedDays.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return formattedDate;
    }

    public static String dateyyyymmdd() {
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return formattedDate;
    }

    public static String addDaysToDate(String date, int daysToAdd) {
        // Define the date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse the input date
        LocalDate localDate = LocalDate.parse(date, formatter);

        // Add the specified number of days
        LocalDate updatedDate = localDate.plusDays(daysToAdd);

        // Format the updated date back to yyyy-MM-dd
        return updatedDate.format(formatter);
    }
}
