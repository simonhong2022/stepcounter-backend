package com.exorlive.stepcounter.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISessionDbRepository extends JpaRepository<Session, String> {

    Optional<Session> findByStartTimeAndActivity(String startTime, String activity);
}
