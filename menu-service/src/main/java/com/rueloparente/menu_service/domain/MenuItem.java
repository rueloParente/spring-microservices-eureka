package com.rueloparente.menu_service.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"description", "createdAt", "updatedAt"})
@Entity
@Table(name = "menu")
class MenuItem {

    /**
     * The unique identifier for the menu item.
     * It is generated using a database sequence named "menu_id_seq".
     * The allocationSize of 50 is crucial for performance, as it allows Hibernate
     * to fetch a batch of 50 IDs from the sequence at once, reducing database roundtrips.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_id_generator")
    @SequenceGenerator(name = "menu_id_generator", sequenceName = "menu_id_seq", allocationSize = 50)
    private Long id;

    /**
     * A unique code for the menu item (e.g., "BURGER-01").
     * Mapped to the "code" column, which must be unique and not null.
     */
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    /**
     * The display name of the menu item (e.g., "Classic Cheeseburger").
     * Mapped to the "name" column, which cannot be null.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * A detailed description of the menu item.
     * Mapped to the "description" column, which is optional.
     */
    @Column(name = "description")
    private String description;

    /**
     * The price of the menu item. Using BigDecimal is the standard
     * and safest way to represent monetary values in Java.
     * Mapped to the "price" column, which cannot be null.
     */
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    /**
     * Indicates whether the menu item is currently available for ordering.
     * Defaults to true, matching the database schema.
     */
    @Column(name = "available", nullable = false)
    private boolean available = true;

    /**
     * The current number of items in stock.
     * Defaults to 0, matching the database schema.
     */
    @Column(name = "current_stock", nullable = false)
    private int currentStock = 0;

    /**
     * The timestamp when this menu item was first created.
     * The @CreationTimestamp annotation automatically sets this field
     * on the first save. It is not updatable.
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when this menu item was last updated.
     * The @UpdateTimestamp annotation automatically updates this field
     * every time the entity is modified.
     */
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * The version number for optimistic locking.
     * The @Version annotation tells the persistence provider to use this field
     * to prevent concurrent modification conflicts. It is automatically managed.
     */
    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}
