package com.exorlive.stepcounter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserModelDbRepository extends JpaRepository<UserModel, String> {
    Optional<UserModel> findByUserEmail(String email);
}
