package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping()
  public List<CropDto> getAllCrops() {
    return cropService.getAllCrops();
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  @GetMapping("/{id}")
  public CropDto getCropById(@PathVariable Integer id) {
    return cropService.getCropById(id);
  }

  /**
   * Gets crop by date.
   *
   * @param start the start
   * @param end   the end
   * @return the crop by date
   */
  @GetMapping("/search")
  public List<CropDto> getCropByDate(@RequestParam LocalDate start, @RequestParam LocalDate end) {
    List<Crop> cropList = cropService.getCropByDate(start, end);
    List<CropDto> cropDtoList = cropList.stream().map(
        crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId()
        )
    ).toList();
    return cropDtoList;
  }

  /**
   * Fertilize crop string.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the string
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String fertilizeCrop(@PathVariable Integer cropId, @PathVariable Integer fertilizerId) {
    return cropService.fertilizeCrop(cropId, fertilizerId);
  }

  /**
   * Gets fertilizers by crop.
   *
   * @param cropId the crop id
   * @return the fertilizers by crop
   */
  @GetMapping("/{cropId}/fertilizers")
  public List<Fertilizer> getFertilizersByCrop(@PathVariable Integer cropId) {
    return cropService.getFertilizersByCrop(cropId);
  }
}
