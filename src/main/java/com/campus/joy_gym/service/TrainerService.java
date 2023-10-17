package com.campus.joy_gym.service;

import com.campus.joy_gym.payload.entity.Trainer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TrainerService {
    ResponseEntity addTrainer(Trainer user);

    ResponseEntity getTrainers();

    Trainer getTrainer(Integer id);

    void updateTrainer(Integer id, Trainer user);

    ResponseEntity deleteTrainers(Trainer trainer);

    long countTrainer();

}
