package com.justdoit.prisongame.pojo;

import com.justdoit.prisongame.utils.NumberGenerator;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Builder
@Data
public class Prisoner implements Comparable<Prisoner> {
    private int id;
    private boolean isBoxFound;
    private int prisonerId;

    public void generatePrisonerId() {
        this.prisonerId = NumberGenerator.pickANumber();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Prisoner prisonerObj) {
            return prisonerObj.prisonerId == this.getPrisonerId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + (this.id * this.prisonerId);
    }

    @Override
    public int compareTo(Prisoner prisoner) {
        return this.id - prisoner.getId();
    }

    public void searchMatchingBox(List<Box> boxes) {
        log.info("Prisoner name {} with #{} looking for his box...", this.id, this.prisonerId);
        int tries = 0;
        int initialPick = NumberGenerator.pickANumber();
        while (tries < 50) {
            tries++;
            log.info("Prisoner #{} looking for his box on his {} try...", this.prisonerId, tries);
            var box = boxes.get(initialPick);
            log.info("Trying box number {} ", box.getId());
            if (!box.isBoxOpen()) {
                box.openBox();
                if (!box.isBoxMatchFound()) {
                    if (box.isBoxPrisonerMatch(this)) {
                        box.matchingPrisonerBoxFound();
                        isBoxFound = true;
                        log.info("!! MATCH FOUND - Box number {} matches prisoner's box number {}", this.prisonerId, box.getId());
                        return;
                    }
                }
                box.closeBox();
            }
        }
    }
}
