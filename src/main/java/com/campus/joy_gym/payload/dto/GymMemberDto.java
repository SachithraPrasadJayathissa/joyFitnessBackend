package com.campus.joy_gym.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymMemberDto {
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
