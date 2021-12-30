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
    }
}
