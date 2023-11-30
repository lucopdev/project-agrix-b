package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private CropService cropService;

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

  @GetMapping("/{id}")
  public CropDto getCropById(@PathVariable Integer id) {
    return cropService.getCropById(id);
  }

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
}
