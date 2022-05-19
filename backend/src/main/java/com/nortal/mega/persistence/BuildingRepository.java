package com.nortal.mega.persistence;

import com.nortal.mega.persistence.entity.BuildingDbo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends CrudRepository<BuildingDbo, Long> {

    List<BuildingDbo> findAll();
}
