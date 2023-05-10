package main.day12;

import main.FileParser;
import main.day11.Day11;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day12Test {
    public static final String DAY = "main/day12";
    FileParser fileParser = new FileParser(getClass());
    List<String> example1 = fileParser.parse(DAY + "/example1");
    List<String> example2 = fileParser.parse(DAY + "/example2");
    List<String> input = fileParser.parse(DAY + "/input");
    Day12 day = new Day12();
//You try contacting the Elves using your handheld device, but the river you're following must be too low to get a decent signal.
//
//You ask the device for a heightmap of the surrounding area (your puzzle input).
// The heightmap shows the local area from above broken into a grid; the elevation of each square of the grid
// is given by a single lowercase letter, where a is the lowest elevation, b is the next-lowest,
// and so on up to the highest elevation, z.
//
//Also included on the heightmap are marks for your current position (S) and the location that should get
// the best signal (E). Your current position (S) has elevation a, and the location that should get the best
// signal (E) has elevation z.
//
//You'd like to reach E, but to save energy, you should do it in as few steps as possible.
// During each step, you can move exactly one square up, down, left, or right.
// To avoid needing to get out your climbing gear, the elevation of the destination square can be at most
//one higher than the elevation of your current square; that is, if your current elevation is m,
// you could step to elevation n, but not to elevation o.
// (This also means that the elevation of the destination square can be much lower than the elevation
// of your current square.)
//
//For example:
//
//Sabqponm
//abcryxxl
//accszExk
//acctuvwj
//abdefghi
//
//Here, you start in the top-left corner; your goal is near the middle. You could start by moving down or right,
// but eventually you'll need to head toward the e at the bottom.
// From there, you can spiral around to the goal:
//SabccdeefghijklmnopqrstuvwxxyzE
//v..v<<<<  Sabqponm
//>v.vv<<^  abcryxxl
//.>vv>E^^  accszExk
//..v>>>^^  acctuvwj
//..>>>>>^  abdefghi
//
//In the above diagram, the symbols indicate whether the path exits each
// square moving up (^), down (v),
// left (<), or right (>). The location that should get the best signal is still E, and . marks unvisited
// squares.
//
//This path reaches the goal in 31 steps, the fewest possible.
//
//What is the fewest steps required to move from your current position to the location that should get the
// best signal?
    @Test
    void shouldReturnShortestPathSize_example1_part1(){
        int size = day.part1(example1);

        assertThat(size).isEqualTo(31);
    }

    @Test
    void shouldReturnShortestPathSize_input_part1(){
        int monkeyBusinessLevel = day.part1(input);

        assertThat(monkeyBusinessLevel).isEqualTo(420);
    }

    @Test
    @Disabled
    void shouldReturnShortestPathSize_input_part2(){
        int monkeyBusinessLevel = day.part2(input);

        assertThat(monkeyBusinessLevel).isEqualTo(414);
    }
}
