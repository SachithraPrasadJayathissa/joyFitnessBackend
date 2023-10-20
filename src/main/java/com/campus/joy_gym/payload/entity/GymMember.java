package com.campus.joy_gym.payload.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GymMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String nic;
    private Integer age;
    private String gender;
    private double height;
    private double weight;
    private double BMI;
    private int workout_time;
    private int workout_experience;
    private String fitness_goal;
    private String schedule;

}
