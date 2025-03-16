package com.justdoit.prisongame.pojo;

import com.justdoit.prisongame.utils.NumberGenerator;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Slf4j
@Builder
@Data
public class Prisoner implements Comparable<Prisoner> {
    private int id;
    private boolean isBoxFound;
    private Box box;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Prisoner prisonerObj) {
            return prisonerObj.id == this.getId();
        }
        return false;
    }

    public void setPrisonerBox(Box box){
        this.box = box;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.id * 15);
    }

    @Override
    public int compareTo(Prisoner prisoner) {
        return this.id - prisoner.getId();
    }

    public void searchMatchingBox(List<Box> boxes) {
        log.info("Prisoner with #{} looking for his box...", this);
        int tries = 0;
        // initial pick
        int prisonerId = NumberGenerator.pickANumber();
        while (tries < 50) {
            var box = boxes.get(prisonerId);
            log.info("Trying box {} ", box);
            if (!box.isBoxMatchFound()) {
                tries++;
                log.info("Prisoner #{} looking for his box on his {} try...", this.getId(), tries);
                if (!box.isBoxOpen()) {
                    box.openBox();
                    if (box.isBoxPrisonerMatch(this)) {
                        box.matchingPrisonerBoxFound();
                        this.isBoxFound = true;
                        this.box = box;
                        log.info("--> !!! MATCH FOUND - Prison #{} found his box at box #{}", this.getId(), box.getId());
                        log.info("--> !!! Prisoner #{}", this);
                        log.info("--> !!! Box #{}", this.box);
                        prisonerId = NumberGenerator.pickANumber();
                        return;
                    }
                }
            }
            box.closeBox();
            prisonerId = box.getPrisonerId();
        }
    }
}
