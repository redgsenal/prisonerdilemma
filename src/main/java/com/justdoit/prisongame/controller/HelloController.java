package com.justdoit.prisongame.controller;

import org.springframework.web.bind.annotation.RestController;

import com.justdoit.prisongame.pojo.Box;
import com.justdoit.prisongame.pojo.Prisoner;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RestController
public class HelloController {


    private List<Prisoner> prisoners = new ArrayList<>();
	private List<Box> boxes = new ArrayList<>();

    @GetMapping("/")
    public String hello() {
        log.info("==> Prisoner Game Started!");
        populatePrisoners();
        populateBoxes();
        log.info("==> Prisoner Game Complete!");
        return "Prisoner Game Complete";
    }

    private void populatePrisoners(){
		log.info("==> Populating 100 prisoners");
		int i = 0;
		while(prisoners.size() < 100){
			var prisoner = Prisoner.builder().id(i++).build();
			prisoners.add(prisoner);
			log.info(">>> Prisoner {} created...", prisoner.getId());
		}
		log.info("==> {} prisoners created", prisoners.size());
	}

	private void populateBoxes() {	
		log.info("==> Populating 100 Boxes");
		int i = 0;
		while (boxes.size() < 100){
			var box = Box.builder().build();
			box.selectPrisonerId();
			if (!boxes.contains(box)){
				box.setId(i++);
				boxes.add(box);
				log.info(">>>> Box {} created with prisoner id {}", box.getId(), box.getPrisonerId());
			}
		}
        log.info("Boxes {}", boxes);
		log.info("==> {} boxes created", boxes.size());
	}
}
