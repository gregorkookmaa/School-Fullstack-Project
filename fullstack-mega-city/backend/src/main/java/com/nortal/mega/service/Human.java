package com.nortal.mega.service;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Human {

    private Integer id;
    private String name;
    private String livesIn;
    private String gender;
    private Integer age;
}
