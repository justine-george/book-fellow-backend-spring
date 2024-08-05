package com.bookfellow.bookfellowbackendspring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
  @Id
  @GeneratedValue
  private UUID id;

  @NotBlank
  @Column(unique = true)
  private String isbn;

  @NotBlank
  private String title;

  @NotBlank
  private String author;

  @NotBlank
  private Integer yearOfPublication;

  @NotBlank
  private String publisher;

  @NotBlank
  private String imageUrlSmall;

  @NotBlank
  private String imageUrlMedium;

  @NotBlank
  private String imageUrlLarge;
}
