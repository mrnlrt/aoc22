package main.day6;

import main.FileParser;

import java.util.List;

public class Day6 {

    public int part1(String sequence) {
        int startOfPacketMarkerSize = 4;

        return detectStartMarker(sequence, startOfPacketMarkerSize);
    }

    private static int detectStartMarker(String sequence, int markerSize) {
        for(var i = 0; i < sequence.length() - markerSize; i++){
            long distinctLetters = sequence
                    .substring(i, i + markerSize)
                    .chars()
                    .distinct()
                    .count();
            if(distinctLetters == markerSize){
                return i + markerSize;
            }
        }
        return 0;
    }

    public int part2(String sequence) {
        int startOfPacketMarkerSize = 14;

        return detectStartMarker(sequence, startOfPacketMarkerSize);

    }
}


