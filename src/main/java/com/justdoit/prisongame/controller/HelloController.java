package com.justdoit.prisongame.controller;

import com.justdoit.prisongame.pojo.Box;
import com.justdoit.prisongame.pojo.Prisoner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
public class HelloController {


    private final List<Prisoner> prisoners = new ArrayList<>();
    private final List<Box> boxes = new ArrayList<>();

    @GetMapping("/")
    public String hello() {
        log.info("==> Prisoner Game Started!");
        populatePrisoners();
        populateBoxes();
        findMatchingPrisonerWithBoxes();
        log.info("==> Prisoner Game Complete!");
        return "Prisoner Game Complete";
    }

    private void findMatchingPrisonerWithBoxes() {
        for (Prisoner prisoner : prisoners) {
            prisoner.searchMatchingBox(boxes);
        }
    }

    private void populatePrisoners() {
        log.info("==> Populating 100 prisoners");
        int i = 0;
        while (prisoners.size() < 100) {
            var prisoner = Prisoner.builder().id(i++).build();
            prisoner.generatePrisonerId();
            if (!prisoners.contains(prisoner)) {
                prisoners.add(prisoner);
                log.info(">>> Prisoner {} created...", prisoner.getId());
            }
        }
        log.info("==> {} prisoners created", prisoners.size());
    }

    private void populateBoxes() {
        log.info("==> Populating 100 Boxes");
        int i = 0;
        while (boxes.size() < 100) {
            var box = Box.builder().build();
            box.pickPrisonerId();
            if (!boxes.contains(box)) {
                box.setId(i++);
                boxes.add(box);
                log.info(">>>> Box {} created with prisoner id {}", box.getId(), box.getPrisonerId());
            }
        }
        log.info("Boxes {}", boxes);
        log.info("==> {} boxes created", boxes.size());
    }
}
