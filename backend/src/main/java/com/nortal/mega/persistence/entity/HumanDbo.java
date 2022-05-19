package com.nortal.mega.persistence.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "human")
public class HumanDbo {

    @Id @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "LivesIn")
    private String livesIn;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private Integer age;

}
