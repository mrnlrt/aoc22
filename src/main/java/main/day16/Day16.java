package main.day16;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Day16 {
    //Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
//Valve BB has flow rate=13; tunnels lead to valves CC, AA
//Valve CC has flow rate=2; tunnels lead to valves DD, BB
//Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
//Valve EE has flow rate=3; tunnels lead to valves FF, DD
//Valve FF has flow rate=0; tunnels lead to valves EE, GG
//Valve GG has flow rate=0; tunnels lead to valves FF, HH
//Valve HH has flow rate=22; tunnel leads to valve GG
//Valve II has flow rate=0; tunnels lead to valves AA, JJ
//Valve JJ has flow rate=21; tunnel leads to valve II
    public long part1(List<String> input) {
        List<Valve> valves = input.stream()
                .map(Valve::new)
                .collect(Collectors.toList());

//        Graph graph = new Graph();
//        for(Valve valve:valves){
//            for(String destinationName:valve.getLeads()){
//                Valve destination = getValve(valves,destinationName);
//                valve.addDestination(destination, destination.getRate());
//            }
//            graph.addValve(valve);
//        }
//        graph = Graph.calculateShortestPathFromSource(graph, getValve(valves,"AA"));


//        List<String> highRates = valves.stream()
//                .filter(valve -> valve.getRate() > LOW_RATE)
//                .map(Valve::getName)
//                .toList();
        Set<String> openValves = new HashSet<>();
//        Set<String> visitedValves = new HashSet<>();

        Valve current = valves.get(0);
        openValves.add("AA");
//        visitedValves.add("AA");
        Set<State> states = new HashSet<>();
        states.add(State.of(getValve(valves, "AA")));
        long totalPressure = 0;
        long openedValvesPressure = 0;
        for (int i = 1; i <= 30; i++) {
            Set<State> currentStates = new HashSet<>();
            for (State state : states) {
                long openValvesPressure = state.increasePressure();
                if (state.currentValveShouldBeOpened()) {
                    state.open();
                    currentStates.add(state);
                    i++;
                }
                state.valve.leads.forEach(lead ->
                        currentStates.add(State.of(state, getValve(valves,lead))));

            }
            states = currentStates;

//            System.out.println("== Minute " + i + " ==");
//            System.out.println("Valves " + openValves + " are opened, releasing "+openedValvesPressure+ " pressure");
//            totalPressure = totalPressure + openedValvesPressure;
//            current = getValve(valves, nextName.get());
//                System.out.println("You move to valve " + current.getName());
//            if (!openValves.contains(current.getName())) {
//                    if (current.getRate() > LOW_RATE || openValves.containsAll(highRates)) {
//                if (current.getRate() > 0) {
//                    i++;
//                    totalPressure = totalPressure + openedValvesPressure;
//                            System.out.println("== Minute " + i + " ==");
//                            System.out.println("Valves " + openValves + " are opened");
//                            System.out.println("You open valve " + current.getName());
                }
//                openValves.add(current.getName());
//                        current.open();
//                openedValvesPressure = openedValvesPressure + current.getRate();
//                    }

//            }

//            }

        return states.stream()
                .mapToLong(state -> state.totalPressure)
                .max()
                .getAsLong();
    }

    private Valve getValve(List<Valve> valves, String s) {
        return valves.stream()
                .filter(valve -> valve.getName().equals(s))
                .findFirst()
                .orElseThrow();
    }


    public int part2(List<String> input) {
        return 0;
    }


}


