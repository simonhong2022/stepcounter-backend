package com.exorlive.stepcounter.body;

import com.exorlive.stepcounter.exception.CustomException;
import com.exorlive.stepcounter.exception.ErrorCode;
import com.exorlive.stepcounter.model.NewBodyDTO;
import com.exorlive.stepcounter.user.IUserModelDbRepository;
import com.exorlive.stepcounter.user.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyService {

    private final IUserModelDbRepository userModelDbRepository;
    private final IBodyDbRepository bodyDbRepository;

    public List<Body> getAllBodyInfo() {return bodyDbRepository.findAll();}

    public Body getSpecificBodyInfo(String id) {
        return bodyDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BODY_NOT_FOUND));
    }

    public Body addBody(NewBodyDTO bodyDTO) {
        UserModel user =userModelDbRepository
                        .findByUserEmail(bodyDTO.email())
                        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        bodyDbRepository
                .findByDateMillis(bodyDTO.dateMillis())
                .ifPresent((info) -> {
                    throw new CustomException(ErrorCode.ALREADY_SAVED_BODY);
                });
        return bodyDbRepository.save(new Body(bodyDTO.dateMillis(), bodyDTO.heartRate(),
                bodyDTO.weight(), bodyDTO.bmi(), bodyDTO.bodyFat(), bodyDTO.muscleMass(),
                bodyDTO.boneMass(), bodyDTO.fatMass(), user));
    }

    public void deleteBody(String id) {
        bodyDbRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BODY_NOT_FOUND));
        bodyDbRepository.deleteById(id);
    }

}
