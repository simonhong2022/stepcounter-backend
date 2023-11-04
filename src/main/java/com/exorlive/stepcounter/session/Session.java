package com.exorlive.stepcounter.session;

import com.exorlive.stepcounter.user.UserModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "session_id")
    private UUID sessionId;
    @Column(name = "activity")
    private String activity;
    @Column(name = "activity_type")
    private Integer activityType;
    private String description;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "calorie_consumed")
    private Float calorieConsumed;
    @Column(name = "avg_heart_rate")
    private Integer avgHeartRate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    public Session(String activity, Integer activityType, String description, String startTime, String endTime, Float calorieConsumed, Integer avgHeartRate, UserModel user) {
        this.activity = activity;
        this.activityType = activityType;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.calorieConsumed = calorieConsumed;
        this.avgHeartRate = avgHeartRate;
        this.user = user;
    }
}
