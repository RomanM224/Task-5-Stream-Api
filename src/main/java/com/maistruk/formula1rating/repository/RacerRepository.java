package com.maistruk.formula1rating.repository;

import static java.util.stream.Collectors.*;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import com.maistruk.formula1rating.domain.Racer;
import com.maistruk.formula1rating.reader.FileReader;
import com.maistruk.formula1rating.service.RacerTimeService;

public class RacerRepository {

    private FileReader fileReader;
    private RacerTimeService racerTimeService;

    public RacerRepository(FileReader fileReader, RacerTimeService racerTimeService) {
        this.fileReader = fileReader;
        this.racerTimeService = racerTimeService;
    }

    public List<Racer> getRacers() {
        Map<String, Duration> racersBestTime = racerTimeService.getRacersBestTime();
            return fileReader.readFromFile("abbreviations.txt")
                    .map(name -> name.split("_"))
                    .map(names -> new Racer(names[0], names[1], names[2]))
                    .peek(racer -> racer.setBestLapTime(racersBestTime.get(racer.getAbbreviation())))
                    .collect(toList());
    }
}
