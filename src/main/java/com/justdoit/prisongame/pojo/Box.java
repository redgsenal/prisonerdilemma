package com.justdoit.prisongame.pojo;

import com.justdoit.prisongame.utils.NumberGenerator;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Builder
@Data
public class Box implements Comparable<Box> {
    private int id;
    @Builder.Default
    private int prisonerId = -1;
    private boolean isBoxMatchFound;
    private boolean isBoxOpen;

    public boolean isBoxPrisonerMatch(Prisoner prisoner) {
        return prisonerId == prisoner.getId();
    }

    public void randomlyPickPrisonerId() {
        this.prisonerId = NumberGenerator.pickANumber();
        // same prisoner id and box id will cause a indefinite loop; not allowed
        if (this.prisonerId == this.id && this.prisonerId > -1){
            log.warn("Same prisoner id and box id not allowed: {}", this.prisonerId);
            log.warn("Box: {}", this);
            randomlyPickPrisonerId();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Box boxObj) {
            return boxObj.prisonerId == this.prisonerId;
        }
        return false;
    }

    public void openBox(){
        this.isBoxOpen = true;
    }

    public void closeBox(){
        this.isBoxOpen = false;
    }

    public void matchingPrisonerBoxFound(){
        this.isBoxMatchFound = true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.id * 5);
    }

    @Override
    public int compareTo(Box box) {
        return this.prisonerId - box.getPrisonerId();
    }
}
