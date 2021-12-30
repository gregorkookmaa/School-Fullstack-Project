package com.nortal.mega.rest;

import com.nortal.mega.service.BuildingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/mega/building")
public class BuildingController {

    private final BuildingService buildingService;
    private final BuildingDtoMapper buildingDtoMapper;

    @GetMapping
    public ResponseEntity<List<BuildingDto>> getAll() {
        return ResponseEntity.ok(buildingService.findAll().stream().map(buildingDtoMapper::map).collect(Collectors.toList()));
    }

    @GetMapping("{buildingId}")
    public ResponseEntity<BuildingDto> getBuildingById(@PathVariable Long buildingId) {
        return ResponseEntity.ok(buildingDtoMapper.map(buildingService.findBuildingById(buildingId)));
    }

    @PostMapping
    public ResponseEntity<BuildingDto> createBuilding(@RequestBody @Valid BuildingDto building) {
        // TODO: Implement create building.
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<BuildingDto> updateBuilding(@RequestBody @Valid BuildingDto building) {
        // TODO: Implement update building.
        return ResponseEntity.ok().build();
    }
}
