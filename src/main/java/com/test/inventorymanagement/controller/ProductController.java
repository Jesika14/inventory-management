package com.test.inventorymanagement.controller;

import com.test.inventorymanagement.dto.ProductCreationResponse;
import com.test.inventorymanagement.dto.ProductDto;
import com.test.inventorymanagement.dto.UpdateQuantityRequest;
import com.test.inventorymanagement.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "2. Products", description = "APIs for managing product inventory")
@SecurityRequirement(name = "bearerAuth") // Applies JWT security to all endpoints in this controller
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @Operation(
      summary = "Add a new product",
      description = "Adds a new product to the inventory. Requires authentication.",
      responses = {
          @ApiResponse(responseCode = "201", description = "Product added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductCreationResponse.class))),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "400", description = "Bad Request - Invalid product data")
      }
  )
  @PostMapping
  public ResponseEntity<ProductCreationResponse> addProduct(@Valid @RequestBody ProductDto productDto) {
    Long productId = productService.addProduct(productDto);
    return new ResponseEntity<>(new ProductCreationResponse(productId), HttpStatus.CREATED);
  }

  @Operation(
      summary = "Get a list of products",
      description = "Retrieves a paginated list of all products in the inventory. Requires authentication.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved products"),
          @ApiResponse(responseCode = "401", description = "Unauthorized")
      }
  )
  @GetMapping
  public ResponseEntity<List<ProductDto>> getProducts(
      @Parameter(description = "Page number to retrieve (0-indexed)") @RequestParam(defaultValue = "0") int page,
      @Parameter(description = "Number of products per page") @RequestParam(defaultValue = "10") int size) {
    Page<ProductDto> productsPage = productService.getProducts(page, size);
    return ResponseEntity.ok(productsPage.getContent());
  }

  @Operation(
      summary = "Update product quantity",
      description = "Updates the quantity of a specific product by its ID. Requires authentication.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Quantity updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "Product not found"),
          @ApiResponse(responseCode = "400", description = "Bad Request - Invalid quantity value")
      }
  )
  @PutMapping("/{id}/quantity")
  public ResponseEntity<ProductDto> updateProductQuantity(
      @Parameter(description = "ID of the product to update") @PathVariable Long id,
      @Valid @RequestBody UpdateQuantityRequest request) {
    ProductDto updatedProduct = productService.updateQuantity(id, request.getQuantity());
    return ResponseEntity.ok(updatedProduct);
  }
}