package com.exorlive.stepcounter.testUtils;

import com.exorlive.stepcounter.model.NewUserDTO;
import com.exorlive.stepcounter.user.UserModel;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class TestData {

    public final String testUserId = "65129d4d661d37420021aeaf";
    public final String testUserName = "testName";
    public final String userEmail = "simonhong85@gmail.com";
    public final String testUserEmail = "test@test.com";
    public final Float testHeight = 180F;
    public final Float testExpectedWeight = 80F;
    public final Integer testAge = 35;
    public final String testSex = "male";
    public final String testMode = "Normal";
    public final Integer testActivityGoal = 30;
    public final Integer updateActivityGoal = 45;
    public final Double testDurationGoal = 25D;
    public final List<String> testSessionRecommendation = new ArrayList<>();
    public final String wrongTestUserEmail = "wrong@wrong.com";


    public final UserModel testUser = new UserModel(testUserName, testUserEmail,testHeight,
            testExpectedWeight,testAge, testSex,testMode,testActivityGoal,testDurationGoal,
            testSessionRecommendation);

    public final NewUserDTO testUserDto = new NewUserDTO(testUserName, testUserEmail,
            testHeight, testExpectedWeight, testAge,testSex,testMode, testActivityGoal,
            testDurationGoal,testSessionRecommendation);

    public final NewUserDTO testUserUpdateDto = new NewUserDTO(testUserName, testUserEmail,
            testHeight, testExpectedWeight, testAge,testSex,testMode, updateActivityGoal,
            testDurationGoal,testSessionRecommendation);

}
