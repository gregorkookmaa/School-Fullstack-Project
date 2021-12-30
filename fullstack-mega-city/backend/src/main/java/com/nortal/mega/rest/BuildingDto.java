package com.nortal.mega.rest;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class BuildingDto {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotEmpty
    private String index;
    @NotEmpty
    private String sectorCode;
    @NotNull
    private Integer energyUnits;
    @NotNull
    private Integer energyUnitMax;
}
