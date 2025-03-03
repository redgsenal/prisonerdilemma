package com.justdoit.prisongame.pojo;

import com.justdoit.prisongame.utils.NumberGenerator;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Prisoner implements Comparable<Prisoner>{
    private int id;
    private Box box;
    private boolean isBoxFound;
    private int prisonerId;

    public void generatePrisonerId(){
        this.prisonerId = NumberGenerator.pickANumber();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Prisoner){
            Prisoner prisonerObj = (Prisoner) obj;
            return prisonerObj.getBox().getPrisonerId() == this.box.getPrisonerId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + (this.id * this.prisonerId);
    }

    @Override
    public int compareTo(Prisoner prisoner){
        return this.id - prisoner.getId();
    }
}
