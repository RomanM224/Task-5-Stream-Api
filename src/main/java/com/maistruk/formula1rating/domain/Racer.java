package com.maistruk.formula1rating.domain;

import java.time.Duration;

public class Racer {

    private String abbreviation;
    private String name;
    private String car;
    private Duration bestLapTime;

    public Racer(String abbreviation, String name, String car) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.car = car;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
    
    public String getName() {
        return name;
    }

    public String getCar() {
        return car;
    }

    public Duration getBestLapTime() {
        return bestLapTime;
    }

    public void setBestLapTime(Duration bestLapTime) {
        this.bestLapTime = bestLapTime;
    }
    
    @Override
    public boolean equals(Object comparedRacer) {
        Racer racer = (Racer) comparedRacer;
        if(!racer.abbreviation.equals(abbreviation)) {
            return false;
        }
        if(!racer.name.equals(name)) {
            return false;
        }
        if(!racer.car.equals(car)) {
            return false;
        }
        if(!racer.bestLapTime.equals(bestLapTime)) {
            return false;
        }
        return true;
    }
}
