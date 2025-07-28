package com.test.inventorymanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {
  @JsonProperty("access_token")
  private String accessToken;

  public AuthResponse(String accessToken) {
    this.accessToken = accessToken;
  }
  // Getter and Setter
  public String getAccessToken() { return accessToken; }
  public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
}
