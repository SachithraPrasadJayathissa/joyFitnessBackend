package com.novozy.spring_new_jwt.service;

import com.novozy.spring_new_jwt.payload.entity.Trainer;
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
