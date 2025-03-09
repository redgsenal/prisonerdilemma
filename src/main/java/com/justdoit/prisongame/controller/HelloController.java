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

    private List<Prisoner> prisoners = new ArrayList<>();
    private List<Box> boxes = new ArrayList<>();

    @GetMapping("/")
    public String hello() {
        log.info("==> Prisoner Game Started!");
        int tries = 0;
        while (!isAllPrisonersFree()) {
            log.info("Do until all prisoners are free...#try {}", tries++);
        }
        log.info("==> Prisoner Game Complete!");
        return "Prisoner Game Complete";
    }

    private void initializePrisonersAndBoxes(){
        populatePrisoners();
        populateBoxes();
    }

    private void doPrisonerDilemma(){
        initializePrisonersAndBoxes();
        findMatchingPrisonerWithBoxes();
    }

    private boolean isAllPrisonersFree() {
        doPrisonerDilemma();
        int numberOfBoxesFound = 0;
        for (Prisoner prisoner : prisoners) {
            if (prisoner.isBoxFound()) {
                numberOfBoxesFound++;
            }
        }
        log.info("Number of prisoners found their boxes: {}", numberOfBoxesFound);
        if (numberOfBoxesFound == 100) {
            log.info("!^ ^! ALL FREE! ^^^^");
            return true;
        }
        log.info("!@ @! ALL BACK TO JAIL!");
        return false;
    }

    private void findMatchingPrisonerWithBoxes() {
        for (Prisoner prisoner : prisoners) {
            prisoner.searchMatchingBox(boxes);
        }
    }

    private void populatePrisoners() {
        log.info("==> Populating 100 prisoners");
        int i = 0;
        // resets prisoners
        prisoners = new ArrayList<>();
        while (prisoners.size() < 100) {
            var prisoner = Prisoner.builder().id(i++).build();
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
        // resets the boxes
        boxes = new ArrayList<>();
        while (boxes.size() < 100) {
            var box = Box.builder().build();
            box.randomlyPickPrisonerId();
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
