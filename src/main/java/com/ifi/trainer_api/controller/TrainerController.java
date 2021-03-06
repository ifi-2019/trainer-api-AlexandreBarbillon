package com.ifi.trainer_api.controller;

import com.ifi.trainer_api.bo.Trainer;
import com.ifi.trainer_api.service.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }
    @GetMapping("/")
    Iterable<Trainer> getAllTrainers(){
        return trainerService.getAllTrainers();
    }
    @GetMapping("/{name}")
    Trainer getTrainer(@PathVariable String name){
        return trainerService.getTrainer(name);
    }
    @PostMapping("/")
    Trainer createTrainer(@RequestBody Trainer trainer){return trainerService.createTrainer(trainer); }
    @DeleteMapping("/{name}")
    void removeTrainer(@PathVariable String name){trainerService.removeTrainer(name);}
}
