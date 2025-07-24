package com.rueloparente.menu_service.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    Optional<MenuItem> findByCode(String code);
}
