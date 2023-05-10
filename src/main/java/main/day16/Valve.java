package main.day16;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;

@Getter
@Setter
public class Valve {
    private boolean opened;
    String name;
    long rate;
    List<String> leads;


    private List<Valve> shortestPath = new LinkedList<>();

    Map<Valve, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Valve destination, int rate) {
        adjacentNodes.put(destination, rate);
    }

//    public Valve(String name) {
//        this.name = name;
//    }

    // getters and setters
    public Valve(String line) {
        String[] valve = line
                .replace("s", "")
                .replace("Valve ", "")
                .replace(" ha flow rate=", ";")
                .replace("; tunnel lead to valve ", ";")
                .replace("; tunnel lead to valve ", ";")
                .split(";");

        this.name = valve[0];
        this.rate = parseInt(valve[1]);
        this.leads = List.of(valve[2].split(", "));
        this.opened = false;
    }

//    public void open() {
//        this.opened = true;
//    }
}
