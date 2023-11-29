package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.model.entities.Farm;

/**
 * The type Farm dto.
 */
public record FarmDto(Integer id, String name, Double size) {

  /**
   * To farm farm.
   *
   * @return the farm
   */
  public Farm toFarm() {
    return new Farm(id, name, size);
  }
}
