package com.rueloparente.menu_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record AddMenuItem(
        @NotBlank(message = "Code is required") @Size(max = 50, message = "Code must be at most 50 characters long") String code,
        @NotBlank(message = "Name is required") @Size(max = 100, message = "Name must be at most 100 characters long") String name,
        @Size(max = 500, message = "Description must be at most 500 characters long") String description,
        @NotNull(message = "Price is required") @Positive(message = "Price must be a positive value") BigDecimal price) {}
