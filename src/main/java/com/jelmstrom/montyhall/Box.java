package com.jelmstrom.montyhall;

class Box {
    private final boolean money;

    Box(boolean money) {
        this.money = money;

    }

    boolean hasPrize(){
        return money;
    }
}
