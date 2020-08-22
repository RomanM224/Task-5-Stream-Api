package com.maistruk.formula1rating.formatter;

import static java.util.stream.Collectors.*;
import static java.lang.System.*;
import static java.util.Comparator.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.maistruk.formula1rating.domain.Racer;

public class TopRacersFormatter {

    public String format(List<Racer> racers, int topRacersNumber) {
        AtomicInteger counter = new AtomicInteger(0);
        return racers.stream()
                .sorted(comparing(Racer::getBestLapTime))
                .map(racer -> buildRacerResult(racer, counter.incrementAndGet(), topRacersNumber))
                .collect(joining());
    }

    private String buildRacerResult(Racer racer, int counter, int topRacersNumber) {
        Duration bestLapTime = racer.getBestLapTime();
        String bestLapTimeFormatted = String.format("%02d:%02d:%03d", bestLapTime.toMinutes(),
                bestLapTime.minusMinutes(bestLapTime.toMinutes()).getSeconds(),
                bestLapTime.minusSeconds(bestLapTime.getSeconds()).toMillis());
        String result = String.format("%d.%-20s | %-30s |%-7s", counter, racer.getName(), racer.getCar(),
                bestLapTimeFormatted) + lineSeparator();
        if (counter == topRacersNumber) {
            return result + repeatChar('-', result.length() - 2) + lineSeparator();
        }
        return result;
    }

    private String repeatChar(char character, int times) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times; i++) {
            result.append(character);
        }
        return result.toString();
    }
}
