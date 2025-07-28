package com.test.inventorymanagement.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProductDto {
  @NotBlank
  private String name;
  private String type;
  @NotBlank
  private String sku;
  private String imageUrl;
  private String description;
  @NotNull @Min(0)
  private Integer quantity;
  @NotNull @DecimalMin("0.0")
  private BigDecimal price;

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getType() { return type; }
  public void setType(String type) { this.type = type; }
  public String getSku() { return sku; }
  public void setSku(String sku) { this.sku = sku; }
  public String getImageUrl() { return imageUrl; }
  public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  public Integer getQuantity() { return quantity; }
  public void setQuantity(Integer quantity) { this.quantity = quantity; }
  public BigDecimal getPrice() { return price; }
  public void setPrice(BigDecimal price) { this.price = price; }
}