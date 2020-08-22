package com.maistruk.formula1rating.service;

import static java.util.stream.Collectors.*;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.maistruk.formula1rating.reader.FileReader;

public class RacerTimeService {

    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'_'HH:mm:ss.SSS");

    private FileReader fileReader;

    public RacerTimeService(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public Map<String, Duration> getRacersBestTime() {
        Map<String, LocalDateTime> startInfo = new HashMap<>();
        Map<String, LocalDateTime> finishInfo = new HashMap<>();
            startInfo = fileReader.readFromFile("start.log").collect(toMap(racerName -> racerName.substring(0, 3),
                    racerTime -> LocalDateTime.parse(racerTime.substring(3), FORMATTER)));
            finishInfo = fileReader.readFromFile("end.log").collect(toMap(racerName -> racerName.substring(0, 3),
                    racerTime -> LocalDateTime.parse(racerTime.substring(3), FORMATTER)));
        Map<String, Duration> racersBestTime = new HashMap<>();
        for (Map.Entry<String, LocalDateTime> entry : startInfo.entrySet()) {
            racersBestTime.put(entry.getKey(), Duration.between(entry.getValue(), finishInfo.get(entry.getKey())));
        }
        return racersBestTime;
    }
}
