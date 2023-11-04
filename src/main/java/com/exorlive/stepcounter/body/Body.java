package com.exorlive.stepcounter.body;

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
@Table(name = "body")
public class Body {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "body_id")
    private UUID bodyId;
    @Column(name = "date_millisecond")
    private String dateMillis;
    @Column(name = "heart_rate")
    private Integer heartRate;
    private Float weight;
    private Float bmi;
    @Column(name = "body_fat")
    private Float bodyFat;
    @Column(name = "muscle_mass")
    private Float muscleMass;
    @Column(name = "bone_mass")
    private Float boneMass;
    @Column(name = "fat_mass")
    private Float fatMass;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    public Body(String dateMillis, Integer heartRate, Float weight, Float bmi, Float bodyFat, Float muscleMass, Float boneMass, Float fatMass, UserModel user) {
        this.dateMillis = dateMillis;
        this.heartRate = heartRate;
        this.weight = weight;
        this.bmi = bmi;
        this.bodyFat = bodyFat;
        this.muscleMass = muscleMass;
        this.boneMass = boneMass;
        this.fatMass = fatMass;
        this.user = user;
    }
}
