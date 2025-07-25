package com.rueloparente.menu_service.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.rueloparente.menu_service.dto.AddMenuItem;
import com.rueloparente.menu_service.dto.MenuItemWeb;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit tests for the {@link MenuItemMapper}.
 * This class leverages Spring's testing support to inject the mapper instance.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MenuItemMapperImpl.class})
class MenuItemMapperTest {

    @Autowired
    private MenuItemMapper menuItemMapper;

    private AddMenuItem addMenuItemDto;
    private MenuItem menuItemEntity;

    @BeforeEach
    void setUp() {
        // Common setup for tests
        addMenuItemDto = new AddMenuItem(
                "PIZZA-MARG",
                "Margherita Pizza",
                "Classic pizza with tomato, mozzarella, and basil.",
                new BigDecimal("12.50"));

        menuItemEntity = new MenuItem();
        menuItemEntity.setId(1L);
        menuItemEntity.setCode("BURGER-CLASSIC");
        menuItemEntity.setName("Classic Burger");
        menuItemEntity.setDescription("A timeless beef burger with cheese, lettuce, and tomato.");
        menuItemEntity.setPrice(new BigDecimal("15.00"));
        menuItemEntity.setAvailable(true);
        menuItemEntity.setCurrentStock(100);
        menuItemEntity.setCreatedAt(LocalDateTime.now().minusDays(1));
        menuItemEntity.setUpdatedAt(LocalDateTime.now());
        menuItemEntity.setVersion(2L);
    }

    @Test
    @DisplayName("Should map AddMenuItem DTO to MenuItem Entity")
    void toEntity_shouldMapCorrectly() {
        // When
        MenuItem result = menuItemMapper.toEntity(addMenuItemDto);

        // Then
        assertThat(result.getCode()).isEqualTo(addMenuItemDto.code());
        assertThat(result.getName()).isEqualTo(addMenuItemDto.name());
        assertThat(result.getDescription()).isEqualTo(addMenuItemDto.description());
        assertThat(result.getPrice()).isEqualTo(addMenuItemDto.price());

        // Verify ignored fields are not set by the mapper
        assertThat(result.getId()).isNull();
        assertThat(result.getVersion()).isNull();
        assertThat(result.getCreatedAt()).isNull();
        assertThat(result.getUpdatedAt()).isNull();
        // Primitive boolean defaults to false, int to 0, which is the desired state
        assertThat(result.isAvailable()).isTrue(); // Default value from entity
        assertThat(result.getCurrentStock()).isZero(); // Default value from entity
    }

    @Test
    @DisplayName("Should map MenuItem Entity to MenuItemWeb DTO")
    void toWeb_shouldMapCorrectly() {
        // When
        MenuItemWeb result = menuItemMapper.toWeb(menuItemEntity);

        // Then
        assertThat(result.getId()).isEqualTo(menuItemEntity.getId());
        assertThat(result.getCode()).isEqualTo(menuItemEntity.getCode());
        assertThat(result.getName()).isEqualTo(menuItemEntity.getName());
        assertThat(result.getDescription()).isEqualTo(menuItemEntity.getDescription());
        assertThat(result.getPrice()).isEqualTo(menuItemEntity.getPrice());
        assertThat(result.isAvailable()).isEqualTo(menuItemEntity.isAvailable());
        assertThat(result.getCurrentStock()).isEqualTo(menuItemEntity.getCurrentStock());
        assertThat(result.getCreatedAt()).isEqualTo(menuItemEntity.getCreatedAt());
        assertThat(result.getUpdatedAt()).isEqualTo(menuItemEntity.getUpdatedAt());
        assertThat(result.getVersion()).isEqualTo(menuItemEntity.getVersion());
    }

    @Test
    @DisplayName("toEntity should return null when input is null")
    void toEntity_withNullInput_shouldReturnNull() {
        // When
        MenuItem result = menuItemMapper.toEntity(null);

        // Then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("toWeb should return null when input is null")
    void toWeb_withNullInput_shouldReturnNull() {
        // When
        MenuItemWeb result = menuItemMapper.toWeb(null);

        // Then
        assertThat(result).isNull();
    }
}
