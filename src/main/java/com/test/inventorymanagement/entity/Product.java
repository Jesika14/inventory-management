package com.test.inventorymanagement.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;
  private String type;
  @Column(nullable = false, unique = true)
  private String sku;
  private String imageUrl;
  @Lob
  private String description;
  @Column(nullable = false)
  private int quantity;
  @Column(nullable = false, scale = 2)
  private BigDecimal price;

  public Long getId() { return id; }
  public String getName() { return name; }
  public String getType() { return type; }
  public String getSku() { return sku; }
  public String getImageUrl() { return imageUrl; }
  public String getDescription() { return description; }
  public int getQuantity() { return quantity; }
  public BigDecimal getPrice() { return price; }

  public void setId(Long id) { this.id = id; }
  public void setName(String name) { this.name = name; }
  public void setType(String type) { this.type = type; }
  public void setSku(String sku) { this.sku = sku; }
  public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
  public void setDescription(String description) { this.description = description; }
  public void setQuantity(int quantity) { this.quantity = quantity; }
  public void setPrice(BigDecimal price) { this.price = price; }
}
