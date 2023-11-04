package com.exorlive.stepcounter.model;

public record NewBodyDTO(
        String dateMillis,
        Integer heartRate,
        Float weight,
        Float bmi,
        Float bodyFat,
        Float muscleMass,
        Float boneMass,
        Float fatMass,

        String email
) {
}
