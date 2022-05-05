package com.nortal.mega.rest;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class HumanDto {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String livesin;
    private String gender;
    @NotNull
    private Integer age;
}
