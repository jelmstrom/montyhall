package com.jelmstrom.montyhall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

class MontyHall {

    private static final Box EMPTY_BOX = new Box(false);
    private static final Box WINNING_BOX = new Box(true);

    public  enum Strategy {
        CHANGE_BOX(boxes ->  {
            int choice = new Random().nextInt(3);
            List<Box> remaining = new ArrayList<>(boxes);
            remaining.remove(choice);
            return remaining.get(0).hasPrize() ? remaining.get(0) : remaining.get(1);
        }),
        KEEP_BOX(boxes -> boxes.get(new Random().nextInt(3)));

        private Function<List<Box>, Box> strategy;
        Strategy(Function<List<Box>, Box> strategy){
            this.strategy = strategy;
        }
    }

    static List<Box> createGame() {
        List<Box> boxes = new ArrayList<>(Arrays.asList(EMPTY_BOX, EMPTY_BOX, EMPTY_BOX));
        boxes.set(new Random().nextInt(3), WINNING_BOX);
        return boxes;
    }

    static Box pickBox(List<Box> boxes, Strategy strategy){
        return strategy.strategy.apply(boxes);
    }
}

