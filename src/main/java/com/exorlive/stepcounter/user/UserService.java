package com.exorlive.stepcounter.user;

import com.exorlive.stepcounter.exception.CustomException;
import com.exorlive.stepcounter.exception.ErrorCode;
import com.exorlive.stepcounter.model.NewUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
