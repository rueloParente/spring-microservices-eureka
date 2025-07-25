package com.rueloparente.menu_service.domain;

import com.rueloparente.menu_service.dto.AddMenuItem;
import com.rueloparente.menu_service.dto.MenuItemWeb;
import com.rueloparente.menu_service.dto.UpdateMenuItem;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

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

    /**
     * Updates an existing MenuItem entity from a DTO.
     * The NullValuePropertyMappingStrategy.IGNORE ensures that any null fields
     * in the DTO are not applied to the entity, enabling partial updates.
     *
     * @param dto The source DTO with update data.
     * @param entity The target entity to be updated.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMenuItem(UpdateMenuItem dto, @MappingTarget MenuItem entity);
}
