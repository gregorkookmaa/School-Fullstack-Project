package com.nortal.mega.persistence;

import com.nortal.mega.persistence.entity.BuildingDbo;
import com.nortal.mega.service.Building;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuildingDboMapper {

    Building map(BuildingDbo dbo);

    BuildingDbo map(Building building);
}
