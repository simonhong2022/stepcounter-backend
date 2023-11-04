package com.exorlive.stepcounter.session;

import com.exorlive.stepcounter.exception.CustomException;
import com.exorlive.stepcounter.exception.ErrorCode;
import com.exorlive.stepcounter.model.NewSessionDTO;
import com.exorlive.stepcounter.user.IUserModelDbRepository;
import com.exorlive.stepcounter.user.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final IUserModelDbRepository userModelDbRepository;
    private final ISessionDbRepository sessionDbRepository;

    public List<Session> getAllSessions() { return sessionDbRepository.findAll();}

    public Session getSpecificSession(String id) {
        return sessionDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.SESSION_NOT_FOUND));
    }

    public Session addSession(NewSessionDTO sessionDTO) {
        UserModel user =userModelDbRepository
                .findByUserEmail(sessionDTO.email())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        sessionDbRepository
                .findByStartTimeAndActivity(sessionDTO.startTime(), sessionDTO.activity())
                .ifPresent((info) -> {
                    throw new CustomException(ErrorCode.SESSION_NOT_FOUND);
                });

        return  sessionDbRepository.save(new Session(sessionDTO.activity(), sessionDTO.activityType(), sessionDTO.description(), sessionDTO.startTime(), sessionDTO.endTime(), sessionDTO.calorieConsumed(), sessionDTO.avgHeartRate(), user));

    }

    public void deleteSession(String id) {
        sessionDbRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.SESSION_NOT_FOUND));
        sessionDbRepository.deleteById(id);
    }
}
