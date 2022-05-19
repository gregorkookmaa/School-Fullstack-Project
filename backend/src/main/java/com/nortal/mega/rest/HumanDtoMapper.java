package com.nortal.mega.rest;

import com.nortal.mega.service.Human;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HumanDtoMapper {

    HumanDto map(Human human);
    Human map(HumanDto human);
}
