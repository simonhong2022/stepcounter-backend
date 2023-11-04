package com.exorlive.stepcounter.model;

import java.util.List;

public record NewUserDTO(
        String userName,
        String userEmail,
        Float height,
        Float expectedWeight,
        Integer age,
        String sex,
        String mode,
        Integer activityGoal,
        Double durationGoal,
        List<String> recommendations

) {
}
