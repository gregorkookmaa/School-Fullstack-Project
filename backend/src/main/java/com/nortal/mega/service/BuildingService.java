package com.nortal.mega.service;

import com.nortal.mega.persistence.BuildingDboMapper;
import com.nortal.mega.persistence.BuildingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingDboMapper buildingDboMapper;

    public List<Building> findAll() {
        return buildingRepository.findAll().stream().map(buildingDboMapper::map).collect(Collectors.toList());
    }

    public Building findBuildingById(Long id) {
        return buildingDboMapper.map(buildingRepository.findById(id).orElseThrow());
    }

    public void save(Building building) {
        // Not sure if this type of valdiation should be here
        if (!building.getIndex().startsWith("NO")) {
            // would prefer to show error: "Index has to start with NO” below the input field."
            return;
        }
        if (building.getEnergyUnitMax() < building.getEnergyUnits()) {
            // would prefer to show error: "This building can stake a maximum of X units."
            return;
        }

        buildingRepository.save(buildingDboMapper.map(building));
    }

    public Long count() {
        return buildingRepository.count();
    }
}
