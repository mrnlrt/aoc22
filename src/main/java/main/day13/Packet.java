package main.day13;

import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.String.join;

public class Packet {
    private final int size;
    private int index=1;
    List<Object> values;

    public Packet(String packet) {
        List<String> strings = Arrays.asList(packet.split(""));
        size = strings.size();
        values = (List<Object>) findSub(strings);

    }

    private Object findSub(List<String> packet) {
        String current = packet.get(index);
        List<Object> currentList = new ArrayList<>();
        while (!current.equals("]")) {
            if (current.equals("[")) {
                index = index + 1;
                Object sub = findSub(packet);
                currentList.add(sub);
            } else if (current.equals(",")) {
                index = index + 1;

            } else {
                if(packet.get(index+1).equals(",") || packet.get(index+1).equals("]")){
                    currentList.add(parseInt(current));
                    index = index + 1;
                }
                else{
                    currentList.add(parseInt(join("",packet.subList(index, index+2))));
                    index = index + 2;
                }

            }
            current = packet.get(index);
        }
        index = index + 1;
        return currentList;
    }

}
