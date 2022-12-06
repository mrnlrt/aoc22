package main.day6;

import main.FileParser;
import main.day5.Day5;
import org.apache.commons.math3.fitting.leastsquares.EvaluationRmsChecker;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day6Test {
    public static final String DAY = "main/day6";
    FileParser fileParser = new FileParser(getClass());
    String input = fileParser.parse(DAY + "/input").get(0);
    Day6 day = new Day6();

    //The preparations are finally complete; you and the Elves leave camp on foot and begin to make your way toward the star fruit grove.
    //
    //As you move through the dense undergrowth, one of the Elves gives you a handheld device.
    // He says that it has many fancy features, but the most important one to set up right now is the communication system.
    //
    //However, because he's heard you have significant experience dealing with signal-based systems,
    // he convinced the other Elves that it would be okay to give you their one malfunctioning device - surely you'll have no problem fixing it.
    //
    //As if inspired by comedic timing, the device emits a few colorful sparks.
    //
    //To be able to communicate with the Elves, the device needs to lock on to their signal.
    // The signal is a series of seemingly-random characters that the device receives one at a time.
    //
    //To fix the communication system, you need to add a subroutine to the device that detects a start-of-packet marker in the datastream.
    // In the protocol being used by the Elves, the start of a packet is indicated by a sequence of four characters that are all different.
    //
    //The device will send your subroutine a datastream buffer (your puzzle input);
    // your subroutine needs to identify the first position where the four most recently received characters were all different.
    // Specifically, it needs to report the number of characters from the beginning of the buffer to the end of the first such four-character marker.
    //
    //For example, suppose you receive the following datastream buffer:
    //
    //mjq jpqm gbljsphdztnvjfqwrcgsmlb
    //
    //After the first three characters (mjq) have been received, there haven't been enough characters received yet to find the marker.
    // The first time a marker could occur is after the fourth character is received, making the most recent four characters mjqj.
    // Because j is repeated, this isn't a marker.
    //
    //The first time a marker appears is after the seventh character arrives. Once it does, the last four characters received are jpqm, which are all different.
    // In this case, your subroutine should report the value 7, because the first start-of-packet marker is complete after 7 characters have been processed.
    //
    //Here are a few more examples:
    //
    //    bvwbjplbgvbhsrlpgdmjqwftvncz: first marker after character 5
    //    nppdvjthqldpwncqszvftbrmjlhg: first marker after character 6
    //    nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg: first marker after character 10
    //    zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw: first marker after character 11
    //
    //How many characters need to be processed before the first start-of-packet marker is detected?
    @Test
    void shouldReturnFirstMarkerPosition_example1_part1(){
        int position = day.part1("mjqjpqmgbljsphdztnvjfqwrcgsmlb");

        assertThat(position).isEqualTo(7);
    }

    @Test
    void shouldReturnFirstMarkerPosition_example2_part1(){
        int position = day.part1("nppdvjthqldpwncqszvftbrmjlhg");

        assertThat(position).isEqualTo(6);
    }

    @Test
    void shouldReturnFirstMarkerPosition_input_part1(){
        int position = day.part1(input);

        assertThat(position).isEqualTo(1578);
    }

    @Test
    void shouldReturnFirstMarkerPosition_example1_part2(){
        int position = day.part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb");

        assertThat(position).isEqualTo(19);
    }

    @Test
    void shouldReturnFirstMarkerPosition_example2_part2(){
        int position = day.part2("nppdvjthqldpwncqszvftbrmjlhg");

        assertThat(position).isEqualTo(23);
    }

    @Test
    void shouldReturnFirstMarkerPosition_input_part2(){
        int position = day.part2(input);

        assertThat(position).isEqualTo(2178);
    }

}