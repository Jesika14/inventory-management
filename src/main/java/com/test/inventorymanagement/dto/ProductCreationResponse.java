package com.test.inventorymanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductCreationResponse {
  @JsonProperty("product_id")
  private Long productId;

  public ProductCreationResponse(Long productId) {
    this.productId = productId;
  }
  // Getter and Setter
  public Long getProductId() { return productId; }
  public void setProductId(Long productId) { this.productId = productId; }
}