package com.betrybe.agrix.service;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.exceptions.CropNotFound;
import com.betrybe.agrix.exceptions.FarmNotFound;
import com.betrybe.agrix.exceptions.FertilizerNotFound;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.CropRepository;
import com.betrybe.agrix.model.repositories.FarmRepository;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private CropRepository cropRepository;
  private FarmRepository farmRepository;
  private FertilizerRepository fertilizerRepository;
  private FarmService farmService;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository       the crop repository
   * @param farmRepository       the farm repository
   * @param farmService          the farm service
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public CropService(CropRepository cropRepository, FarmRepository farmRepository,
      FarmService farmService, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.farmRepository = farmRepository;
    this.farmService = farmService;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Create crop crop.
   *
   * @param crop the crop
   * @param id   the id
   * @return the crop
   * @throws FarmNotFound the farm not found
   */
  public Crop createCrop(Crop crop, Integer id) throws FarmNotFound {
    Farm farm = farmService.getFarmById(id);

    crop.setFarm(farm);
    return cropRepository.save(crop);
  }

  /**
   * Gets crops by id.
   *
   * @param id the id
   * @return the crops by id
   * @throws FarmNotFound the farm not found
   */
  public List<CropDto> getCropsById(Integer id) throws FarmNotFound {
    Optional<Farm> optionalFarm = farmRepository.findById(id);
    if (optionalFarm.isEmpty()) {
      throw new FarmNotFound();
    }

    List<Crop> crops = optionalFarm.get().getCrop();

    return crops.stream().map(newCrops -> {
      CropDto cropDto = new CropDto(
          newCrops.getId(),
          newCrops.getName(),
          newCrops.getPlantedArea(),
          newCrops.getPlantedDate(),
          newCrops.getHarvestDate(),
          newCrops.getFarm().getId()
      );
      return cropDto;
    }).toList();
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  public List<CropDto> getAllCrops() {
    List<Farm> farms = farmRepository.findAll();

    return farms.stream()
        .flatMap(farm -> farm.getCrop().stream()
            .map(crop -> new CropDto(
                crop.getId(),
                crop.getName(),
                crop.getPlantedArea(),
                crop.getPlantedDate(),
                crop.getHarvestDate(),
                crop.getFarm().getId()
            ))
        ).toList();
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   * @throws FarmNotFound the farm not found
   */
  public CropDto getCropById(Integer id) throws FarmNotFound {
    Optional<Crop> optionalCrop = cropRepository.findById(id);
    if (optionalCrop.isEmpty()) {
      throw new CropNotFound();
    }

    CropDto cropDto = new CropDto(
        optionalCrop.get().getId(),
        optionalCrop.get().getName(),
        optionalCrop.get().getPlantedArea(),
        optionalCrop.get().getPlantedDate(),
        optionalCrop.get().getHarvestDate(),
        optionalCrop.get().getFarm().getId()
    );
    return cropDto;
  }

  /**
   * Gets crop by date.
   *
   * @param start the start
   * @param end   the end
   * @return the crop by date
   */
  public List<Crop> getCropByDate(LocalDate start, LocalDate end) {
    List<Crop> cropsList = cropRepository.findAll();

    cropsList = cropsList.stream()
        .filter(crop -> crop.getHarvestDate().isBefore(end) && crop.getHarvestDate().isAfter(start))
        .toList();

    return cropsList;
  }

  /**
   * Fertilize crop string.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the string
   * @throws CropNotFound       the crop not found
   * @throws FertilizerNotFound the fertilizer not found
   */
  public String fertilizeCrop(Integer cropId, Integer fertilizerId)
      throws CropNotFound, FertilizerNotFound {
    Optional<Crop> cropOptional = cropRepository.findById(cropId);
    Optional<Fertilizer> fertilizerOptional = fertilizerRepository.findById(fertilizerId);

    if (cropOptional.isEmpty()) {
      throw new CropNotFound();
    }
    if (fertilizerOptional.isEmpty()) {
      throw new FertilizerNotFound();
    }

    cropOptional.get().getFertilizerList().add(fertilizerOptional.get());
    cropRepository.save(cropOptional.get());

    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * Gets fertilizers by crop.
   *
   * @param cropId the crop id
   * @return the fertilizers by crop
   * @throws CropNotFound the crop not found
   */
  public List<Fertilizer> getFertilizersByCrop(Integer cropId) throws CropNotFound {
    Optional<Crop> cropOptional = cropRepository.findById(cropId);

    if (cropOptional.isEmpty()) {
      throw new CropNotFound();
    }

    return cropOptional.get().getFertilizerList();
  }
}

