package com.test.inventorymanagement.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  public Long getId() { return id; }
  @Override public String getUsername() { return username; }
  @Override public String getPassword() { return password; }

  public void setId(Long id) { this.id = id; }
  public void setUsername(String username) { this.username = username; }
  public void setPassword(String password) { this.password = password; }

  @Override public Collection<? extends GrantedAuthority> getAuthorities() { return Collections.emptyList(); }
  @Override public boolean isAccountNonExpired() { return true; }
  @Override public boolean isAccountNonLocked() { return true; }
  @Override public boolean isCredentialsNonExpired() { return true; }
  @Override public boolean isEnabled() { return true; }
}
