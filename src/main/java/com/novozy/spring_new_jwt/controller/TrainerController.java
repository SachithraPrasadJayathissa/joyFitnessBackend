package com.novozy.spring_new_jwt.controller;

import com.novozy.spring_new_jwt.payload.entity.Trainer;
import com.novozy.spring_new_jwt.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @PostMapping("/add")
    public ResponseEntity addTrainer(@RequestBody Trainer trainer) {
        return trainerService.addTrainer(trainer);
    }

    @GetMapping
    public ResponseEntity getAllTrainers()
    {
        return trainerService.getTrainers();
    }

    @GetMapping("/get")
    public Trainer getTrainerById(@RequestParam Integer id) {
        return trainerService.getTrainer(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateTrainerById(@PathVariable Integer id, @RequestBody Trainer trainer) {
        trainerService.updateTrainer(id, trainer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteTrainer(@RequestBody Trainer trainer){
        return trainerService.deleteTrainers(trainer);
    }

    @GetMapping("/countTrainer")
    public long countTrainer() {
        return trainerService.countTrainer();
    }
}
