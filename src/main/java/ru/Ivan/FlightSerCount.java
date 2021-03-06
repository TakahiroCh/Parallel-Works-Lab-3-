package ru.Ivan;

import java.io.Serializable;

public class FlightSerCount implements Serializable {

    private float maxArrDelay;
    private int countOfFlights;
    private int countOfDelays;
    private int countOfCancelled;

    public FlightSerCount() {}

    public FlightSerCount(int countOfFlights, int countOfDelays, float maxArrDelay, int countOfCancelled) {
        this.countOfFlights = countOfFlights;
        this.countOfDelays = countOfDelays;
        this.maxArrDelay = maxArrDelay;
        this.countOfCancelled = countOfCancelled;
    }

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
        return new FlightSerCount(a.getCountOfFlights() + 1,
                isDelayed ? a.getCountOfDelays() + 1 : a.getCountOfDelays(),
                Math.max(a.getMaxArrDelay(), maxArrDelay),
                isCancelled ? a.getCountOfCancelled() + 1 : a.getCountOfCancelled());
    }

    public static FlightSerCount add(FlightSerCount a, FlightSerCount b) {
        return new FlightSerCount(a.getCountOfFlights() + b.getCountOfFlights(),
                a.getCountOfDelays() + b.getCountOfDelays(),
                Math.max(a.getMaxArrDelay(), b.getMaxArrDelay()),
                a.getCountOfCancelled() + b.getCountOfCancelled());
    }

    public static String toOutString(FlightSerCount a) {
        float percentOfDelays = (float) a.getCountOfDelays() / a.getCountOfFlights() * 100;
        float percentOfCancelled = (float) a.getCountOfCancelled() / a.getCountOfFlights() * 100;
        return "INFO : { MaxDelay: " + a.getMaxArrDelay() +
                "; Flights : "  + a.getCountOfFlights() +
                "; Delays : " + a.getCountOfDelays() +
                "; Cancelled : " + a.getCountOfCancelled() +
                "}, % of Delays : " + percentOfDelays +
                "%, % of Cancelled : " + percentOfCancelled +
                "%.";
    }


}
