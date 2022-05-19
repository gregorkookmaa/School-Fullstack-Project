package com.nortal.mega.rest;

import com.nortal.mega.service.Human;
import com.nortal.mega.service.HumanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/mega/human")
public class HumanController {

    private final HumanService humanService;
    private final HumanDtoMapper humanDtoMapper;

    @GetMapping
    public ResponseEntity<List<HumanDto>> getAll() {
        return ResponseEntity.ok(humanService.findAll().stream().map(humanDtoMapper::map).collect(Collectors.toList()));
    }

    @GetMapping("{humanId}")
    public ResponseEntity<HumanDto> getHumanById(@PathVariable Long humanId) {
        return ResponseEntity.ok(humanDtoMapper.map(humanService.findHumanById(humanId)));
    }

    @PostMapping
    public ResponseEntity<HumanDto> createHuman(@RequestBody @Valid HumanDto human) {
        humanService.save(humanDtoMapper.map(human));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<HumanDto> updateHuman(@RequestBody @Valid HumanDto human) {
        Human humanInDatabase = humanService.findHumanById(human.getId());

        humanService.save(humanDtoMapper.map(human));
        return ResponseEntity.ok().build();
    }
}
