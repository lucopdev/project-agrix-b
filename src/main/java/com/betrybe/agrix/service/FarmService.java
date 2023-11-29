package com.betrybe.agrix.service;

import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.exceptions.FarmNotFound;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmService {

  private FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<FarmDto> getFarms() {
    List<Farm> farms = farmRepository.findAll();

    return farms.stream().map(farm -> {
      FarmDto farmDto = new FarmDto(
          farm.getId(),
          farm.getName(),
          farm.getSize()
      );
      return farmDto;
    }).toList();
  }

  public Farm getFarmById(Integer id) throws FarmNotFound {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFound();
    }

    return optionalFarm.get();
  }
}
