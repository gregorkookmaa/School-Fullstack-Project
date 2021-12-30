package com.nortal.mega;

import com.nortal.mega.persistence.BuildingRepository;
import com.nortal.mega.persistence.entity.BuildingDbo;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Arrays;

@AllArgsConstructor
@SpringBootApplication
public class Main {

    private final BuildingRepository buildingRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDb() {
        BuildingDbo building0 = new BuildingDbo().setId(1L).setName("Big Building").setAddress("Highway 420").setIndex("NO600").setEnergyUnits(400).setEnergyUnitMax(500).setSectorCode("CX60");
        BuildingDbo building1 = new BuildingDbo().setId(2L).setName("Small Building").setAddress("Highway 1200").setIndex("NO90000").setEnergyUnits(20).setEnergyUnitMax(100).setSectorCode("CX12");

        buildingRepository.saveAll(Arrays.asList(building0, building1));
    }
}
