package com.novozy.spring_new_jwt.controller;

import com.novozy.spring_new_jwt.payload.entity.Trainer;
import com.novozy.spring_new_jwt.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    /**
     * add trainer
     */

    @PostMapping("/add")
    public ResponseEntity addTrainer(@RequestBody Trainer trainer) {
        return trainerService.addTrainer(trainer);
    }

    /**
     * get trainer as list
     */

    @GetMapping
    public ResponseEntity getAllTrainers()
    {
        return trainerService.getTrainers();
    }

    /**
     * get trainer by id
     */

    @GetMapping("/get")
    public Trainer getTrainerById(@RequestParam Integer id) {
        return trainerService.getTrainer(id);
    }

    /**
     * update trainer
     */

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateTrainerById(@PathVariable Integer id, @RequestBody Trainer trainer) {
        trainerService.updateTrainer(id, trainer);
        return ResponseEntity.noContent().build();
    }

    /**
     * delete trainer
     */

    @DeleteMapping("delete")
    public ResponseEntity deleteTrainer(@RequestBody Trainer trainer){
        return trainerService.deleteTrainers(trainer);
    }

    @GetMapping("/counttrainer")
    public long countTrainer() {
        return trainerService.countTrainer();
    }
}
