package com.justdoit.prisongame.utils;

public final class NumberGenerator {
    
    public static int pickANumber(){
        return pickANumber(0, 100);
    }

    public static int pickANumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }


}
