package ru.Ivan;

import java.io.Serializable;

public class FlightSerializable implements Serializable {

    private int destAirportID;
    private int originAirportID;
    private float arrDelay;
    private float cancelled;

    public FlightSerializable() {}

    public FlightSerializable(int destAirportID, int originAirportID, float arrDelay, float cancelled) {
        this.destAirportID = destAirportID;
        this.originAirportID = originAirportID;
        this.arrDelay = arrDelay;
        this.cancelled = cancelled;
    }

    public float getArrDelay() {
        return arrDelay;
    }

    public float getCancelled() {
        return cancelled;
    }

    public int getDestAirportID() {
        return destAirportID;
    }

    public int getOriginAirportID() {
        return originAirportID;
    }
}
