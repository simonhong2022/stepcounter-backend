package com.exorlive.stepcounter.user;

import com.exorlive.stepcounter.body.Body;
import com.exorlive.stepcounter.session.Session;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.sql.In;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_email")
    private String userEmail;
    private Float height;
    @Column(name = "expected_weight")
    private Float expectedWeight;
    private Integer age;
    private String sex;
    private String mode;
    @Column(name = "activity_goal")
    private Integer activityGoal;
    @Column(name = "duration_goal")
    private Double durationGoal;
    @Column(name = "session_recommendation")
    private List<String> sessionRecommendation;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Body> bodyList;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Session> sessionList;

    public UserModel(String userName, String userEmail, Float height, Float expectedWeight, Integer age, String sex, String mode, Integer activityGoal, Double durationGoal, List<String> sessionRecommendation) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.height = height;
        this.expectedWeight = expectedWeight;
        this.age = age;
        this.sex = sex;
        this.mode = mode;
        this.activityGoal = activityGoal;
        this.durationGoal = durationGoal;
        this.sessionRecommendation = sessionRecommendation;
        this.bodyList = new ArrayList<>();
        this.sessionList = new ArrayList<>();
    }
}

