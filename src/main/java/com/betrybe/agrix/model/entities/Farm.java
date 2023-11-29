package com.betrybe.agrix.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * The type Farm.
 */
@Entity
@Table(name = "farms")
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private Double size;

  @OneToMany(
      mappedBy = "farm",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  @JsonIgnore
  private List<Crop> crops;

  /**
   * Instantiates a new Farm.
   */
  public Farm() {
  }

  /**
   * Instantiates a new Farm.
   *
   * @param id   the id
   * @param name the name
   * @param size the size
   */
  public Farm(Integer id, String name, Double size) {
    this.id = id;
    this.name = name;
    this.size = size;
  }

  /**
   * Gets crop.
   *
   * @return the crop
   */
  public List<Crop> getCrop() {
    return crops;
  }

  /**
   * Sets crop.
   *
   * @param crops the crop
   */
  public void setCrop(List<Crop> crops) {
    this.crops = crops;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets size.
   *
   * @return the size
   */
  public Double getSize() {
    return size;
  }

  /**
   * Sets size.
   *
   * @param size the size
   */
  public void setSize(Double size) {
    this.size = size;
  }
}
