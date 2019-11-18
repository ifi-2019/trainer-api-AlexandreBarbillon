package com.ifi.trainer_api.repository;

import com.ifi.trainer_api.bo.Trainer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TrainerRepository extends CrudRepository<Trainer, String> {

}
