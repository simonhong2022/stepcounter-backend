package com.exorlive.stepcounter.model;

public record NewSessionDTO(
        String activity,
        Integer activityType,
        String description,
        String startTime,
        String endTime,
        Float calorieConsumed,
        Integer avgHeartRate,
        String email
) {
}
