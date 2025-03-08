package com.justdoit.prisongame.pojo;

import com.justdoit.prisongame.utils.NumberGenerator;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Box implements Comparable<Box> {
    private int id;
    private int prisonerId = -1;
    private boolean isBoxMatchFound;
    private boolean isBoxOpen;

    public boolean isBoxPrisonerMatch(Prisoner prisoner) {
        return prisonerId == prisoner.getPrisonerId();
    }

    public void pickPrisonerId() {
        this.prisonerId = NumberGenerator.pickANumber();
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
        return super.hashCode() + (this.id * this.prisonerId);
    }

    @Override
    public int compareTo(Box box) {
        return this.prisonerId - box.getPrisonerId();
    }
}
