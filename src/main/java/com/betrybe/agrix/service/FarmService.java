package com.betrybe.agrix.service;

import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.exceptions.FarmNotFound;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private FarmRepository farmRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * Insert farm farm.
   *
   * @param farm the farm
   * @return the farm
   */
  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Gets farms.
   *
   * @return the farms
   */
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

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws FarmNotFound the farm not found
   */
  public Farm getFarmById(Integer id) throws FarmNotFound {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFound();
    }

    return optionalFarm.get();
  }
}
