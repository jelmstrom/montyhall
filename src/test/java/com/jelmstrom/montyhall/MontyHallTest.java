package com.jelmstrom.montyhall;

import org.assertj.core.data.Offset;
import org.junit.Test;
import java.util.*;

import static com.jelmstrom.montyhall.MontyHall.Strategy.CHANGE_BOX;
import static com.jelmstrom.montyhall.MontyHall.Strategy.KEEP_BOX;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
public class MontyHallTest {

    private final int runs = 100000;

    @Test
    public void choosingBoxWithoutChanging(){
        long wins = range(0, runs).filter(ex -> {
                List<Box> boxes = MontyHall.createGame();
                return  MontyHall.pickBox(boxes, KEEP_BOX).hasPrize();
        }).count();

        double winPercentage = wins*100.0 / runs;
        System.out.println("Choosing box with the changing box strategy has a success rate of " + winPercentage +" % over " + runs + " games");
        assertThat(winPercentage).isCloseTo(33.33, Offset.offset(2.0));
    }

    @Test
    public void choosingBoxWithChanging(){
        long wins = range(0, runs).filter(ex -> {
            List<Box> boxes = MontyHall.createGame();
            return  MontyHall.pickBox(boxes, CHANGE_BOX).hasPrize();
        }).count();
        double winPercentage = wins * 100.0 / runs;
        System.out.println("Choosing box with the changing box strategy has a success rate of " + winPercentage +" % over " + runs + " games");
        assertThat(winPercentage).isCloseTo(66.66, within(2.0));
    }
}
