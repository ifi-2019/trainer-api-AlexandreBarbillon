package com.ifi.trainer_api.service;

import com.ifi.trainer_api.bo.Trainer;
import com.ifi.trainer_api.repository.TrainerRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {
    private TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Iterable<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public Trainer getTrainer(String name) {
        return trainerRepository.findById(name).get();
    }

    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }
}
