package com.campus.joy_gym.repository;

import com.campus.joy_gym.payload.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    @Query(value = "SELECT * FROM trainer where nic = ?1", nativeQuery = true)
    Optional<Trainer> exitsByNIC(String nic);
    Optional<Trainer> findByName(String username);


}
