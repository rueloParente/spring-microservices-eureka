package com.rueloparente.menu_service.web.controller;

import com.rueloparente.menu_service.domain.MenuItemService;
import com.rueloparente.menu_service.dto.AddMenuItem;
import com.rueloparente.menu_service.dto.MenuItemWeb;
import com.rueloparente.menu_service.dto.PagedResult;
import com.rueloparente.menu_service.dto.UpdateMenuItem;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing menu items.
 * Provides endpoints for creating, retrieving, updating, and listing menu items.
 */
@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    /**
     * Handles the creation of a new menu item.
     *
     * @param addMenuItem DTO containing the data for the new item.
     * @return A ResponseEntity with status 201 (Created) and the created item in the body.
     */
    @PostMapping
    public ResponseEntity<MenuItemWeb> addMenuItem(@Valid @RequestBody AddMenuItem addMenuItem) {
        MenuItemWeb createdItem = menuItemService.addMenuItem(addMenuItem);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                  .path("/{code}")
                                                  .buildAndExpand(createdItem.getCode())
                                                  .toUri();

        return ResponseEntity.created(location).body(createdItem);
    }

    /**
     * Handles the update of an existing menu item.
     *
     * @param code           The code of the menu item to update.
     * @param updateMenuItem DTO containing the new data.
     * @return The updated menu item.
     */
    @PutMapping("/{code}")
    public ResponseEntity<MenuItemWeb> updateMenuItem(
            @PathVariable String code, @Valid @RequestBody UpdateMenuItem updateMenuItem) {
        MenuItemWeb updatedItem = menuItemService.updateMenuItem(code, updateMenuItem);
        return ResponseEntity.ok(updatedItem);
    }

    /**
     * Retrieves a single menu item by its unique code.
     *
     * @param code The code of the menu item.
     * @return The found menu item.
     */
    @GetMapping("/{code}")
    public ResponseEntity<MenuItemWeb> findMenuItemByCode(@PathVariable String code) {
        MenuItemWeb menuItem = menuItemService.findMenuItemByCode(code);
        return ResponseEntity.ok(menuItem);
    }

    /**
     * Retrieves a paginated list of all menu items.
     *
     * @param pageNo The page number to retrieve, defaulting to 1.
     * @return A PagedResult containing the list of menu items.
     */
    @GetMapping
    public ResponseEntity<PagedResult<MenuItemWeb>> findAllMenuItems(@RequestParam(defaultValue = "1") int pageNo) {
        PagedResult<MenuItemWeb> result = menuItemService.findAllMenuItems(pageNo);
        return ResponseEntity.ok(result);
    }
}
