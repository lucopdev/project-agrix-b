package com.betrybe.agrix.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

/**
 * The type Crop dto.
 */
public record CropDto(
    Integer id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,

    @JsonProperty("farmId") Integer farmId) {

}
