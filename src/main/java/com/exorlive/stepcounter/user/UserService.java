package com.exorlive.stepcounter.user;

import com.exorlive.stepcounter.exception.CustomException;
import com.exorlive.stepcounter.exception.ErrorCode;
import com.exorlive.stepcounter.model.NewUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserModelDbRepository userModelDbRepository;

    public List<UserModel> getAllUsers() { return userModelDbRepository.findAll();}

    public UserModel getUserByEmail(String email) {
        return userModelDbRepository
                .findByUserEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    public UserModel getUserById(String id) {
        return userModelDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    public UserModel addUser(NewUserDTO userDTO) {
        userModelDbRepository
                .findByUserEmail(userDTO.userEmail())
                .ifPresent((info) -> {
                    throw new CustomException(ErrorCode.ALREADY_SAVED_USER);
                });
        return userModelDbRepository.save(new UserModel(userDTO.userName(),
                userDTO.userEmail(), userDTO.height(), userDTO.expectedWeight(),
                userDTO.age(), userDTO.sex(), userDTO.mode(), userDTO.activityGoal(),
                userDTO.durationGoal(), userDTO.recommendations()));
    }

    public UserModel updateUser(String email, NewUserDTO updateUserDTO) {
        UserModel storedUser = userModelDbRepository
                .findByUserEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        if (updateUserDTO.userName() != null && !updateUserDTO.userName().isEmpty()) {
            storedUser.setUserName(updateUserDTO.userName());
        }
        if (updateUserDTO.userEmail() != null && !updateUserDTO.userEmail().isEmpty()) {
            storedUser.setUserEmail(updateUserDTO.userEmail());
        }
        if (updateUserDTO.sex() != null && !updateUserDTO.sex().isEmpty()) {
            storedUser.setSex(updateUserDTO.sex());
        }
        if (updateUserDTO.mode() != null && !updateUserDTO.mode().isEmpty()) {
            storedUser.setMode(updateUserDTO.mode());
        }
        if (updateUserDTO.expectedWeight() != null && updateUserDTO.expectedWeight() >= 0) {
            storedUser.setExpectedWeight(updateUserDTO.expectedWeight());
        }
        if (updateUserDTO.height() != null && updateUserDTO.height() >= 0) {
            storedUser.setHeight(updateUserDTO.height());
        }
        if (updateUserDTO.age() != null && updateUserDTO.age() >= 0) {
            storedUser.setAge(updateUserDTO.age());
        }
        if (updateUserDTO.activityGoal() != null && updateUserDTO.activityGoal() >= 0) {
            storedUser.setActivityGoal(updateUserDTO.activityGoal());
        }
        if (updateUserDTO.durationGoal() != null && updateUserDTO.durationGoal() >= 0) {
            storedUser.setDurationGoal(updateUserDTO.durationGoal());
        }

        return userModelDbRepository.save(storedUser);
    }

    public void deleteUser(String id) {
        userModelDbRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
        userModelDbRepository.deleteById(id);
    }

    public void deleteUserByEmail(String email) {
        UserModel userModel = userModelDbRepository.findByUserEmail(email).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
        userModelDbRepository.deleteById(userModel.getUserId());
    }

}
