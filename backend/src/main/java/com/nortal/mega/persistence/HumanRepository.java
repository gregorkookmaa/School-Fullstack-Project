package com.nortal.mega.persistence;

import com.nortal.mega.persistence.entity.HumanDbo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HumanRepository extends CrudRepository<HumanDbo, Long> {

    List<HumanDbo> findAll();
}
