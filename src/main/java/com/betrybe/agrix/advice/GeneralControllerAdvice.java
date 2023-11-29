package com.betrybe.agrix.advice;

import com.betrybe.agrix.exceptions.CropNotFound;
import com.betrybe.agrix.exceptions.FarmNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type General controller advice.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  /**
   * Handle not found response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler({
      FarmNotFound.class
  })
  public ResponseEntity<String> handleFarmNotFound(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
  }

  @ExceptionHandler({
      CropNotFound.class
  })
  public ResponseEntity<String> handleCropNotFound(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
  }
}
