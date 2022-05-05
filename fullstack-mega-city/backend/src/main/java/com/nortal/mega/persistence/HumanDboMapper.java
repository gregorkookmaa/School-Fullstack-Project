package com.nortal.mega.persistence;

import com.nortal.mega.persistence.entity.HumanDbo;
import com.nortal.mega.service.Human;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HumanDboMapper {

    Human map(HumanDbo dbo);

    HumanDbo map(Human human);
}
