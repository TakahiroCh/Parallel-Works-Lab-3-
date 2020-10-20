package ru.Ivan;

import java.io.Serializable;

public class FlightSerCount implements Serializable {

    private float maxArrDelay;
    private int countOfFlights;
    private int countOfDelays;
    private int countOfCancelled;

    public FlightSerCount() {}

    public float getMaxArrDelay() {
        return maxArrDelay;
    }

    public int getCountOfDelays() {
        return countOfDelays;
    }

    public int getCountOfFlights() {
        return countOfFlights;
    }

    public int getCountOfCancelled() {
        return countOfCancelled;
    }

    public static FlightSerCount addValue(FlightSerCount a, float maxArrDelay, boolean isDelayed, boolean isCancelled) {
        
    }


}
