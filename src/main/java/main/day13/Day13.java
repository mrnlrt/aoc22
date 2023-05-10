package main.day13;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class Day13 {
    public int part1(List<String> packets) {
        List<List<Packet>> packetsPairs = getPairs(packets);
        int count = 0;
        for (var i = 0; i < packetsPairs.size(); i++) {
            List<Packet> pair = packetsPairs.get(i);
            if (isInOrder(pair.get(0).values, pair.get(1).values) >= 0) {
                count = count + i + 1;
            }
        }
        return count;
    }

    public int part2(List<String> input) {
        List<List<Integer>> DIVIDER_1 = List.of(List.of(2));
        List<List<Integer>> DIVIDER_2 = List.of(List.of(6));
        int divider_one_index = 0;
        int divider_two_index = 0;
        List<Packet> packets = getPackets(input);
        for (Packet packet : packets) {
            if (isInOrder(packet.values, DIVIDER_1) >= 0) {
                divider_one_index = divider_one_index + 1;
            }
            if (isInOrder(packet.values, DIVIDER_2) >= 0) {
                divider_two_index = divider_two_index + 1;
            }
        }
        return (divider_one_index+1) * ( divider_two_index + 2);
    }

    private List<Packet> getPackets(List<String> input) {
        List<Packet> packets = new ArrayList<>();
        for (String packet : input) {
            if (!packet.isBlank()) {
                packets.add(new Packet(packet));
            }
        }
        return packets;
    }


    private List<List<Packet>> getPairs(List<String> input) {
        List<List<Packet>> packets = new ArrayList<>();
        Packet previousPacket = null;
        for (String packet : input) {
            if (!packet.isBlank()) {
                if (isNull(previousPacket)) {
                    previousPacket = new Packet(packet);

                } else {
                    packets.add(List.of(previousPacket, new Packet(packet)));
                    previousPacket = null;

                }
            }
        }
        return packets;
    }

    private int isInOrder(Object left, Object right) {
        boolean leftIsNumber = left instanceof Integer;
        boolean rightIsNumber = right instanceof Integer;
        if (leftIsNumber && rightIsNumber) {
            return (Integer) right - (Integer) left;
        } else if (leftIsNumber) {
            return isInOrder(List.of(left), right);
        } else if (rightIsNumber) {
            return isInOrder(left, List.of(right));
        } else {
            List<Object> leftArray = (List<Object>) left;
            List<Object> rightArray = (List<Object>) right;
            int comparisonSize = leftArray.size();
            try {
                int isInOrder;
                for (var i = 0; i < comparisonSize; i++) {
                    isInOrder = isInOrder(leftArray.get(i), rightArray.get(i));
                    if (isInOrder != 0) {
                        return isInOrder;
                    }
                }
                return rightArray.size() - leftArray.size();
            } catch (IndexOutOfBoundsException e) {
                return -1;
            }
        }
    }

}

