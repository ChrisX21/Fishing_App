package com.example.fishingapp.Model;

public class WeatherResponse {
    private Current current;
    private Location location;

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Current getCurrent() {
        return current;
    }
    public void setCurrent(Current current) {
        this.current = current;
    }
}
