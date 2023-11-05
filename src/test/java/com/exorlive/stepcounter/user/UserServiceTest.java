package com.exorlive.stepcounter.user;

import com.exorlive.stepcounter.StepcounterApplication;
import com.exorlive.stepcounter.exception.CustomException;
import com.exorlive.stepcounter.model.NewUserDTO;
import com.exorlive.stepcounter.session.SessionService;
import com.exorlive.stepcounter.testUtils.TestData;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Autowired
    UserService userService;
    @AfterAll
    void tearDown(){
        userService.deleteUserByEmail(TestData.testUser.getUserEmail());
        //sessionService.deleteSession(TestData.setupSurveyDTO.id());
    }

    @Test
    @Order(1)
    void getCorrectAllUsersSize() {
        int zeroLength = 0;
        int actualResult = userService.getAllUsers().size();
        assertNotEquals(zeroLength, actualResult);
    }

    @Test
    @Order(2)
    void getWrongAllUsersSize() {
        int allOrgLength = 10;
        int result = userService.getAllUsers().size();
        assertNotEquals(allOrgLength, result);
    }

    @Test
    @Order(3)
    void getCorrectUserByEmail() {
        UserModel user = userService.getUserByEmail(TestData.userEmail);
        System.out.println(user.getUserEmail());
        assertEquals(user.getUserEmail(), TestData.userEmail);
    }

    @Test
    @Order(4)
    void getWrongUserByEmail() {
        CustomException exception = assertThrows(CustomException.class, () -> {
            userService.getUserByEmail((TestData.wrongTestUserEmail));
        });
        String expectedMessage = "User ID not exist";
        String actualMessage = exception.getErrorCode().getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @Order(5)
    void shouldAddAndDeleteUser() {
        UserModel userModel= userService.addUser(TestData.testUserDto);
        assertNotNull(userModel.getUserId());
        assertEquals(TestData.testUserEmail, userModel.getUserEmail());

        int userQuantityBeforeDeletion = userService.getAllUsers().size();
        userService.deleteUser(userModel.getUserId());
        int userQuantityAfterDeletion = userService.getAllUsers().size();

        assertNotEquals(userQuantityAfterDeletion, userQuantityBeforeDeletion);
    }

    @Test
    @Order(6)
    void existingUserEmailThrowExceptionOnCreatingUser() {

        UserModel userModel = userService.addUser(TestData.testUserDto);

        CustomException exception =
                assertThrows(CustomException.class, () -> {
                    userService.addUser(new NewUserDTO(userModel.getUserName(),
                            userModel.getUserEmail(), userModel.getHeight(),
                            userModel.getExpectedWeight(), userModel.getAge(),
                            userModel.getSex(), userModel.getMode(), userModel.getActivityGoal(),
                            userModel.getDurationGoal(), userModel.getSessionRecommendation()));
                });

        String expectedMessage = "User is already saved";
        String actualMessage = exception.getErrorCode().getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @Order(7)
    public void updateActivityGoal() {
        UserModel userModel = userService.updateUser(TestData.testUser.getUserEmail(), TestData.testUserUpdateDto);
        System.out.println(userModel.getUserEmail());
        System.out.println(userModel.getActivityGoal());
        assertNotEquals(userModel.getActivityGoal(), TestData.testActivityGoal);
        assertEquals(userModel.getActivityGoal(), TestData.updateActivityGoal);
    }


}
