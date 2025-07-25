package com.rueloparente.menu_service.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * DTO for updating an existing MenuItem.
 * Using a Java Record for an immutable data carrier.
 */
public record UpdateMenuItem(
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters") String name,
        @Size(max = 255, message = "Description cannot be more than 255 characters") String description,
        @Positive(message = "Price must be a positive value") BigDecimal price,
        boolean available,
        @PositiveOrZero(message = "Current stock must be zero or positive") Integer currentStock) {}
