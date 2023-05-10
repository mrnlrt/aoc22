package main.day16;

import java.util.*;
import java.util.stream.Collectors;

public class Day16bis {

    public record State(Map<String, Long> open, Valve valve, long totalPressure) {}
    public record State2(Map<String, Long> open, Valve me, Valve elephant, long totalFlow) {}

    public Object part1(List<String> input) {
        Map<String, Valve> valves = input(input);
        Set<State> states = new HashSet<>();
        states.add(new State(new HashMap<>(), valves.get("AA"), 0));
        for(int minutes = 0; minutes<30; minutes++) {
            Set<State> newStates = new HashSet<>();
            for(State state : states) {
                long openValvesPressure = state.open.values()
                                    .stream()
                                    .mapToLong(e -> e)
                                    .sum()
                            + state.totalPressure;
                if(state.valve.rate > 0 && !state.open.containsKey(state.valve.name)) {
                    Map<String, Long> newOpen = new HashMap<>(state.open);
                    newOpen.put(state.valve.name, state.valve.rate);
                    newStates.add(new State(newOpen, state.valve, openValvesPressure));
                }
                state.valve.leads.forEach(name ->
                        newStates.add(new State(state.open, valves.get(name), openValvesPressure)));
            }
            states = newStates;
        }
        return states.stream()
                .mapToLong(State::totalPressure)
                .max()
                .getAsLong();
    }

    private Map<String, Valve> input(List<String> input) {
        return input.stream()
                .map(main.day16.Valve::new)
                .collect(Collectors.toMap(v -> v.name, v -> v));
    }


//    private List<State2> openValve(Valve v1, Valve v2, boolean both, Map<String, Valve> valves, State2 s, long flow) {
//        if(v1.flow > 0 && !s.open.containsKey(v1.name) && (!both || (v2.flow > 0 && !s.open.containsKey(v2.name)))) {
//            Map<String, Long> newOpen = new HashMap<>(s.open);
//            newOpen.put(v1.name, v1.flow);
//            if(both) {
//                newOpen.put(v2.name, v2.flow);
//                return of(new State2(newOpen, v1, v2, flow));
//            }
//            return v2.others.stream().map(name -> new State2(newOpen, v1, valves.get(name), flow)).toList();
//        }
//        return of();
//    }
}
