package com.exorlive.stepcounter.body;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBodyDbRepository extends JpaRepository<Body, String> {
    Optional<Body> findByDateMillis(String dateMills);
}
