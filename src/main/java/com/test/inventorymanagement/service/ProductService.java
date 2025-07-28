package com.test.inventorymanagement.service;

import com.test.inventorymanagement.dto.ProductDto;
import com.test.inventorymanagement.entity.Product;
import com.test.inventorymanagement.exception.ProductNotFoundException;
import com.test.inventorymanagement.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional
  public Long addProduct(ProductDto productDto) {
    Product product = new Product();
    mapDtoToEntity(productDto, product);
    Product savedProduct = productRepository.save(product);
    return savedProduct.getId();
  }

  @Transactional(readOnly = true)
  public Page<ProductDto> getProducts(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Product> productPage = productRepository.findAll(pageable);
    return productPage.map(this::mapEntityToDto);
  }

  @Transactional
  public ProductDto updateQuantity(Long id, int quantity) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    product.setQuantity(quantity);
    Product updatedProduct = productRepository.save(product);
    return mapEntityToDto(updatedProduct);
  }

  private void mapDtoToEntity(ProductDto dto, Product entity) {
    entity.setName(dto.getName());
    entity.setType(dto.getType());
    entity.setSku(dto.getSku());
    entity.setImageUrl(dto.getImageUrl());
    entity.setDescription(dto.getDescription());
    entity.setQuantity(dto.getQuantity());
    entity.setPrice(dto.getPrice());
  }

  private ProductDto mapEntityToDto(Product entity) {
    ProductDto dto = new ProductDto();
    dto.setName(entity.getName());
    dto.setType(entity.getType());
    dto.setSku(entity.getSku());
    dto.setImageUrl(entity.getImageUrl());
    dto.setDescription(entity.getDescription());
    dto.setQuantity(entity.getQuantity());
    dto.setPrice(entity.getPrice());
    return dto;
  }
}
