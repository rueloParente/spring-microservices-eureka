package com.rueloparente.menu_service.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.rueloparente.menu_service.ApplicationProperties;
import com.rueloparente.menu_service.domain.exceptions.ResourceNotFoundException;
import com.rueloparente.menu_service.dto.AddMenuItem;
import com.rueloparente.menu_service.dto.MenuItemWeb;
import com.rueloparente.menu_service.dto.PagedResult;
import com.rueloparente.menu_service.dto.UpdateMenuItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * Unit tests for the MenuItemService.
 * Uses Mockito to isolate dependencies and test business logic in isolation.
 */
@ExtendWith(MockitoExtension.class)
class MenuItemServiceTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private MenuItemMapper menuItemMapper;

    @Mock
    private ApplicationProperties properties;

    @InjectMocks
    private MenuItemService menuItemService;

    @Test
    void addMenuItem_shouldSucceed() {
        // Given
        var addDto = new AddMenuItem("NEW-01", "New Item", "Desc", new BigDecimal("10.00"));
        var menuItem = new MenuItem(); // Assume mapper creates this
        var webDto = MenuItemWeb.builder().id(1L).code("NEW-01").build(); // Assume mapper creates this

        when(menuItemRepository.findByCode("NEW-01")).thenReturn(Optional.empty());
        when(menuItemMapper.toEntity(addDto)).thenReturn(menuItem);
        when(menuItemRepository.save(menuItem)).thenReturn(menuItem);
        when(menuItemMapper.toWeb(menuItem)).thenReturn(webDto);

        // When
        MenuItemWeb result = menuItemService.addMenuItem(addDto);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo("NEW-01");
    }

    @Test
    void addMenuItem_shouldFailWhenCodeExists() {
        // Given
        var addDto = new AddMenuItem("EXIST-01", "Existing Item", "Desc", new BigDecimal("10.00"));
        when(menuItemRepository.findByCode("EXIST-01")).thenReturn(Optional.of(new MenuItem()));

        // When & Then
        assertThatThrownBy(() -> menuItemService.addMenuItem(addDto))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("A menu item with code 'EXIST-01' already exists.");
    }

    @Test
    void updateMenuItem_shouldSucceed() {
        // Given
        var updateDto = new UpdateMenuItem("Updated Name", "Updated Desc", new BigDecimal("12.50"), true, 100);
        var existingItem = new MenuItem();
        existingItem.setCode("UPDATE-01");
        var webDto = MenuItemWeb.builder()
                .id(1L)
                .code("UPDATE-01")
                .name("Updated Name")
                .build();

        when(menuItemRepository.findByCode("UPDATE-01")).thenReturn(Optional.of(existingItem));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(existingItem);
        when(menuItemMapper.toWeb(existingItem)).thenReturn(webDto);

        // When
        MenuItemWeb result = menuItemService.updateMenuItem("UPDATE-01", updateDto);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Updated Name");
    }

    @Test
    void updateMenuItem_shouldFailWhenNotFound() {
        // Given
        var updateDto = new UpdateMenuItem("Updated Name", "Desc", new BigDecimal("12.50"), true, 100);
        when(menuItemRepository.findByCode("NON-EXISTENT")).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> menuItemService.updateMenuItem("NON-EXISTENT", updateDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("MenuItem with code NON-EXISTENT not found");
    }

    @Test
    void findMenuItemByCode_shouldSucceed() {
        // Given
        var existingItem = new MenuItem();
        var webDto = MenuItemWeb.builder().id(1L).code("FIND-01").build();
        when(menuItemRepository.findByCode("FIND-01")).thenReturn(Optional.of(existingItem));
        when(menuItemMapper.toWeb(existingItem)).thenReturn(webDto);

        // When
        MenuItemWeb result = menuItemService.findMenuItemByCode("FIND-01");

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo("FIND-01");
    }

    @Test
    void findMenuItemByCode_shouldFailWhenNotFound() {
        // Given
        when(menuItemRepository.findByCode(anyString())).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> menuItemService.findMenuItemByCode("NON-EXISTENT"))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void findAllMenuItems_shouldReturnPagedResult() {
        // Given
        int pageNo = 1;
        int pageSize = 10;
        var menuItem = new MenuItem();
        var webDto = MenuItemWeb.builder().id(1L).build();
        Page<MenuItem> page = new PageImpl<>(List.of(menuItem));

        when(properties.pageSize()).thenReturn(pageSize);
        when(menuItemRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(menuItemMapper.toWeb(menuItem)).thenReturn(webDto);

        // When
        PagedResult<MenuItemWeb> result = menuItemService.findAllMenuItems(pageNo);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.data()).hasSize(1);
        assertThat(result.pageNumber()).isEqualTo(1);
        assertThat(result.totalElements()).isEqualTo(1);
    }
}
