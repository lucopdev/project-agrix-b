package com.betrybe.agrix.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Crop dto.
 */
public record CropDto(
    Integer id,
    String name,
    Double plantedArea,
    @JsonProperty("farmId") Integer farmId) {

}
