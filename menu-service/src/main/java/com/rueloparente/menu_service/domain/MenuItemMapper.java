package com.rueloparente.menu_service.domain;

import com.rueloparente.menu_service.dto.AddMenuItem;
import com.rueloparente.menu_service.dto.MenuItemWeb;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface MenuItemMapper {

    /**
     * Maps an AddMenuItem DTO to a MenuItem entity.
     * The 'id', 'available', 'currentStock', 'createdAt', 'updatedAt', and 'version'
     * fields are ignored as they are not present in the source DTO or are managed by the database/JPA.
     *
     * @param dto The source AddMenuItem DTO.
     * @return The mapped MenuItem entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "available", ignore = true)
    @Mapping(target = "currentStock", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    MenuItem toEntity(AddMenuItem dto);

    /**
     * Maps a MenuItem entity to a MenuItemWeb DTO.
     *
     * @param entity The source MenuItem entity.
     * @return The mapped MenuItemWeb DTO.
     */
    MenuItemWeb toWeb(MenuItem entity);
}
