package com.rueloparente.menu_service.web.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.rueloparente.menu_service.AbstractIT;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

/**
 * Component tests for the {@link MenuItemController} using REST Assured.
 * These tests start a real server and send HTTP requests to the endpoints.
 */
@Sql("/test-data.sql")
public class MenuItemControllerComponentTest extends AbstractIT {

    @Test
    @DisplayName("POST /api/menu-items - Should create a new menu item successfully")
    void shouldCreateMenuItem() {
        Map<String, Object> newMenuItem = new HashMap<>();
        newMenuItem.put("code", "TACO-BEEF");
        newMenuItem.put("name", "Beef Taco");
        newMenuItem.put("description", "A classic beef taco with all the fixings.");
        newMenuItem.put("price", 8.75);

        given().contentType(ContentType.JSON)
                .body(newMenuItem)
                .when()
                .post("/api/menu-items")
                .then()
                .statusCode(201) // Created
                .header("Location", containsString("/api/menu-items/TACO-BEEF"))
                .body("code", equalTo("TACO-BEEF"))
                .body("name", equalTo("Beef Taco"))
                .body("price", is(8.75F)); // JSON numbers are treated as float by default in Hamcrest
    }

    @Test
    @DisplayName("POST /api/menu-items - Should return 400 for duplicate code")
    void shouldNotCreateMenuItemWithDuplicateCode() {
        Map<String, Object> duplicateMenuItem = new HashMap<>();
        duplicateMenuItem.put("code", "BURGER-CLASSIC"); // This code already exists in test-data.sql
        duplicateMenuItem.put("name", "Another Burger");
        duplicateMenuItem.put("description", "A burger that should not be created.");
        duplicateMenuItem.put("price", 16.00);

        given().contentType(ContentType.JSON)
                .body(duplicateMenuItem)
                .when()
                .post("/api/menu-items")
                .then()
                .statusCode(500); // Or 409 Conflict, depending on specific error handling
    }

    @Test
    @DisplayName("GET /api/menu-items/{code} - Should return menu item for existing code")
    void shouldFindMenuItemByCode() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/menu-items/PIZZA-MARG")
                .then()
                .statusCode(200) // OK
                .body("code", equalTo("PIZZA-MARG"))
                .body("name", equalTo("Margherita Pizza"))
                .body("currentStock", equalTo(50));
    }

    @Test
    @DisplayName("GET /api/menu-items/{code} - Should return 404 for non-existent code")
    void shouldReturnNotFoundForNonExistentCode() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/menu-items/NON-EXISTENT")
                .then()
                .statusCode(404); // Not Found
    }

    @Test
    @DisplayName("PUT /api/menu-items/{code} - Should update an existing menu item")
    void shouldUpdateMenuItem() {
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("name", "Super Caesar Salad");
        updatedData.put("description", "An enhanced version of our classic Caesar salad.");
        updatedData.put("price", 11.50);
        updatedData.put("currentStock", 90);
        updatedData.put("available", true);

        given().contentType(ContentType.JSON)
                .body(updatedData)
                .when()
                .put("/api/menu-items/SALAD-CAESAR")
                .then()
                .statusCode(200)
                .body("name", equalTo("Super Caesar Salad"))
                .body("price", is(11.50F))
                .body("currentStock", equalTo(90));
    }

    @Test
    @DisplayName("GET /api/menu-items - Should return a paginated list of menu items")
    void shouldFindAllMenuItemsPaginated() {
        given().contentType(ContentType.JSON)
                .queryParam("pageNo", 1)
                .when()
                .get("/api/menu-items")
                .then()
                .statusCode(200)
                .body("totalElements", equalTo(12))
                .body("pageNumber", equalTo(1));
    }
}
