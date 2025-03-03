package com.justdoit.prisongame.pojo;

import com.justdoit.prisongame.utils.NumberGenerator;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Box implements Comparable<Box>{
    private int id;
    private int prisonerId;
    private boolean isBoxMatchFound;
    public boolean isBoxPrisonMatch(Prisoner prisoner){
        return this.id == prisoner.getId();
    }
    public void selectPrisonerId(){
        this.prisonerId = NumberGenerator.pickANumber();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Box){
            Box boxObj = (Box) obj;
            return boxObj.prisonerId == this.prisonerId;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + (this.id * this.prisonerId);
    }

    @Override
    public int compareTo(Box box) {
        return this.id - box.getId();
    }
}
