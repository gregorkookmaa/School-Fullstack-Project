package com.nortal.mega.service;

import com.nortal.mega.persistence.HumanDboMapper;
import com.nortal.mega.persistence.HumanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HumanService {

    private final HumanRepository humanRepository;
    private final HumanDboMapper humanDboMapper;

    public List<Human> findAll() {
        return humanRepository.findAll().stream().map(humanDboMapper::map).collect(Collectors.toList());
    }

    public Human findHumanById(Long id) {
        return humanDboMapper.map(humanRepository.findById(id).orElseThrow());
    }

    public void save(Human human) {

        humanRepository.save(humanDboMapper.map(human));
    }

    public Long count() {
        return humanRepository.count();
    }
}
