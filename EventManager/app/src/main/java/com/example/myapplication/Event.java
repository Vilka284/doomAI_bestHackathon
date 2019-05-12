package com.example.myapplication;

public class Event {
    public String currentEventString, currentEventDescriptionString;
    public double lattitude, longitude;

    public double getLongitude() {
        return longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public String getCurrentEventDescriptionString() {
        return currentEventDescriptionString;
    }

    public String getCurrentEventString() {
        return currentEventString;
    }

    public Event(String currentEventString, String currentEventDescriptionString, double lattitude, double longitude) {
        this.currentEventString = currentEventString;
        this.currentEventDescriptionString = currentEventDescriptionString;
        this.lattitude = lattitude;
        this.longitude = longitude;

    }
}
