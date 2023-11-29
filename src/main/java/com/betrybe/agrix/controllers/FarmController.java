package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.exceptions.FarmNotFound;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   * @param cropService the crop service
   */
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Create farm response entity.
   *
   * @param farmDto the farm dto
   * @return the response entity
   */
  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody FarmDto farmDto) {
    Farm farm = farmService.insertFarm(farmDto.toFarm());
    FarmDto newFarmDto = new FarmDto(farm.getId(), farm.getName(), farm.getSize());
    return newFarmDto;
  }

  /**
   * Gets farms.
   *
   * @return the farms
   */
  @GetMapping()
  public List<FarmDto> getFarms() {
    return farmService.getFarms();
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws FarmNotFound the farm not found
   */
  @GetMapping("/{id}")
  public FarmDto getFarmById(@PathVariable Integer id) throws FarmNotFound {
    Farm farm = farmService.getFarmById(id);
    FarmDto farmDto = new FarmDto(farm.getId(), farm.getName(), farm.getSize());
    return farmDto;
  }

  /**
   * Insert crop crop dto.
   *
   * @param crop the crop
   * @param id   the id
   * @return the crop dto
   */
  @PostMapping("/{id}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto insertCrop(@RequestBody Crop crop, @PathVariable Integer id) {
    Crop newCrop = cropService.createCrop(crop, id);
    CropDto cropDto = new CropDto(newCrop.getId(), newCrop.getName(), newCrop.getPlantedArea(),
        newCrop.getFarm().getId());
    return cropDto;
  }

  /**
   * Gets crops by id.
   *
   * @param id the id
   * @return the crops by id
   */
  @GetMapping("/{id}/crops")
  public List<CropDto> getCropsById(@PathVariable Integer id) {
    return cropService.getCropsById(id);
  }
}
