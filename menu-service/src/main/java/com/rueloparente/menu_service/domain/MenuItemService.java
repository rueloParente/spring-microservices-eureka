package com.rueloparente.menu_service.domain;

import com.rueloparente.menu_service.ApplicationProperties;
import com.rueloparente.menu_service.domain.exceptions.ResourceNotFoundException;
import com.rueloparente.menu_service.dto.AddMenuItem;
import com.rueloparente.menu_service.dto.MenuItemWeb;
import com.rueloparente.menu_service.dto.PagedResult;
import com.rueloparente.menu_service.dto.UpdateMenuItem;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Handles the business logic for menu item operations.
 */
@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;
    private final ApplicationProperties properties;

    /**
     * Creates and persists a new MenuItem.
     * It first checks if a menu item with the same code already exists to prevent duplicates.
     *
     * @param addMenuItem The DTO with the data for the new item.
     * @return The created menu item, converted to a MenuItemWeb DTO.
     * @throws IllegalStateException if a menu item with the same code already exists.
     */
    @Transactional
    public MenuItemWeb addMenuItem(AddMenuItem addMenuItem) {
        Optional<MenuItem> existingItem = menuItemRepository.findByCode(addMenuItem.code());
        if (existingItem.isPresent()) {
            throw new IllegalStateException("A menu item with code '" + addMenuItem.code() + "' already exists.");
        }

        MenuItem menuItem = menuItemMapper.toEntity(addMenuItem);

        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        return menuItemMapper.toWeb(savedMenuItem);
    }

    /**
     * Updates an existing menu item.
     *
     * @param code           The code of the menu item to update.
     * @param updateMenuItem DTO containing the new data for the menu item.
     * @return The updated menu item, converted to a MenuItemWeb DTO.
     * @throws ResourceNotFoundException if no menu item with the given code is found.
     */
    @Transactional
    public MenuItemWeb updateMenuItem(String code, UpdateMenuItem updateMenuItem) {
        MenuItem menuItem = menuItemRepository
                .findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem with code " + code + " not found"));

        menuItemMapper.updateMenuItem(updateMenuItem, menuItem);

        MenuItem updatedMenuItem = menuItemRepository.save(menuItem);
        return menuItemMapper.toWeb(updatedMenuItem);
    }

    /**
     * Retrieves a single menu item by its code.
     *
     * @param code The code of the menu item to retrieve.
     * @return A MenuItemWeb DTO representing the found item.
     * @throws ResourceNotFoundException if no menu item with the given code is found.
     */
    @Transactional
    public MenuItemWeb findMenuItemByCode(String code) {
        return menuItemRepository
                .findByCode(code)
                .map(menuItemMapper::toWeb)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem with code " + code + " not found"));
    }

    /**
     * Retrieves a paginated list of all menu items.
     *
     * @param pageNo The page number to retrieve (1-based index).
     * @return A PagedResult containing the menu items for the requested page.
     */
    public PagedResult<MenuItemWeb> findAllMenuItems(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        PageRequest pageable = PageRequest.of(pageNo, properties.pageSize(), sort);
        Page<MenuItemWeb> productsPage = menuItemRepository.findAll(pageable).map(menuItemMapper::toWeb);

        return new PagedResult<>(
                productsPage.getContent(),
                productsPage.getTotalElements(),
                productsPage.getNumber() + 1,
                productsPage.getTotalPages(),
                productsPage.isFirst(),
                productsPage.isLast(),
                productsPage.hasNext(),
                productsPage.hasPrevious());
    }
}
