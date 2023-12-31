package com.campus.joy_gym.service.impl;


import com.campus.joy_gym.payload.dto.MessageResponse;
import com.campus.joy_gym.payload.entity.Trainer;
import com.campus.joy_gym.payload.entity.UserInfo;
import com.campus.joy_gym.repository.TrainerRepository;
import com.campus.joy_gym.repository.UserInfoRepository;
import com.campus.joy_gym.service.TrainerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder encoder;

    private static Logger LOGGER = LogManager.getLogger("TrainerServiceLogger");


    @Override
    public ResponseEntity addTrainer(Trainer user) {
        try {
            Optional<Trainer> trainer = trainerRepository.exitsByNIC(user.getNic());
            if (!trainer.isPresent()) {
                Trainer save = trainerRepository.save(user);
                UserInfo userInfo = new UserInfo();
                userInfo.setPassword(encoder.encode(user.getPassword()));
                userInfo.setName(user.getUsername());
                userInfo.setRoles("ROLE_TRAINER");
                userInfoRepository.save(userInfo);
                LOGGER.info("TrainerController | TrainerService | addTrainer | " + save);
                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success",
                        "Successfully Saved Trainer.", save), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Failed",
                        "Trainer Already Exists : " + user.getNic(), user.getNic()), HttpStatus.OK);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Error",
                    ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getTrainers() {
        try {
            List<Trainer> getAllTrainers = trainerRepository.findAll();
            if (!getAllTrainers.isEmpty()) {
                LOGGER.info("TrainerController | TrainerService | getTrainers | " + getAllTrainers);
                return new ResponseEntity<>(getAllTrainers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageResponse(null), HttpStatus.OK);
            }

        } catch (Exception ex) {
            LOGGER.info("TrainerController | TrainerService | getTrainers | " + ex);
            return new ResponseEntity<>(new MessageResponse(ex), HttpStatus.OK);
        }
    }
    @Override
    public Trainer getTrainer(Integer id) {
        Trainer user = trainerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:" + id));

        return user;
    }

    @Override
    public ResponseEntity deleteTrainers(Trainer thetrainer) {

        Optional<Trainer> trainer = trainerRepository.exitsByNIC(thetrainer.getNic());
        Trainer oldObj = trainer.get();
        try {
            if (trainer.isPresent()) {
                trainerRepository.delete(oldObj);
                LOGGER.info("GymTrainerController | TrainerService | deleteTrainer | " + oldObj.getNic());

                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success",
                        "Successfully Delete Trainer.", oldObj.getNic()), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Failed",
                        "There are no user in this ID : " + thetrainer.getNic(), thetrainer.getNic()), HttpStatus.OK);
            }

        } catch (Exception ex) {
            LOGGER.info("GymTrainerController | TrainerService | deleteTrainer | " + ex);
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error",
                    "An error occurred while deleting trainer."), HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public long countTrainer() {
        try {
            long count = trainerRepository.count();
            LOGGER.info("GymTrainerController | TrainerService | countTrainer | " + count);
            return count;
        }catch (Exception ex){
            LOGGER.info("GymTrainerController | TrainerService | countTrainer | " + ex);
            return 0;
        }
    }
}
