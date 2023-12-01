package com.betrybe.agrix.service;

import com.betrybe.agrix.exceptions.FertilizerNotFound;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FertilizerService {

  private FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getFertilizers() {
    return fertilizerRepository.findAll();
  }

  public Fertilizer getFertilizerById(Integer fertilizerId) throws FertilizerNotFound {
    Optional<Fertilizer> fertilizerOptional = fertilizerRepository.findById(fertilizerId);

    if (fertilizerOptional.isEmpty()) {
      throw new FertilizerNotFound();
    }
    return fertilizerOptional.get();
  }
}
