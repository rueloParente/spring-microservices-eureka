package com.rueloparente.menu_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuItemWeb {

    private Long id;
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean available;
    private int currentStock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long version;
}
