package main.day16;

import java.util.HashSet;
import java.util.Set;

public class State {
    Set<String> openedValves;
    Valve valve;
    long totalPressure;
    long openedValvesPressure;

    public State(Set<String> openedValves, Valve valve, long totalPressure, long openedValvesPressure) {

        this.openedValves = openedValves;
        this.valve = valve;
        this.totalPressure = totalPressure;
        this.openedValvesPressure = openedValvesPressure;
    }

    public long increasePressure(){
        totalPressure = totalPressure + openedValvesPressure;
        return totalPressure;
    }
    public void open(){
        openedValvesPressure = openedValvesPressure + valve.getRate();
        openedValves.add(valve.getName());
        increasePressure();
    }
    public boolean currentValveShouldBeOpened(){
        return valve.rate > 0 && !openedValves.contains(valve.name);
    }
    public static State of(Valve valve){
        return new State(new HashSet<>(),valve, 0, 0);
    }
    public static State of(State state, Valve next){
        return new State(state.openedValves, next, state.totalPressure, state.openedValvesPressure);
    }
}
