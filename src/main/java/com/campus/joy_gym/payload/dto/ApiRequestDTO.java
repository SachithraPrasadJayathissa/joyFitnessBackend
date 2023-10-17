package com.campus.joy_gym.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRequestDTO {

    private int age;
    private int workout_experience;
    private int workout_time;
    private double weight;
    private double height;
    private double bmi;
    private String gender;
    private String fitness_goal;

}
