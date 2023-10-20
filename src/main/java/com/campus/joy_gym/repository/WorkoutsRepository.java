package com.campus.joy_gym.repository;

import com.campus.joy_gym.payload.entity.Workouts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutsRepository extends JpaRepository<Workouts, Long> {

    @Query(value = "Select * from workouts where scheduleName =?1", nativeQuery = true)
    Workouts getWorkoutsBySchedule(String schedule);
}
