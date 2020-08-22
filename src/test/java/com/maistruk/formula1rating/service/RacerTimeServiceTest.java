package com.maistruk.formula1rating.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.maistruk.formula1rating.reader.FileReader;

public class RacerTimeServiceTest {

    private FileReader fileReader;
    private RacerTimeService racerTimeService;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        fileReader = mock(FileReader.class);
        racerTimeService = new RacerTimeService(fileReader);
        Stream<String> streamFromStartLog = Stream.of("SVF2018-05-24_12:02:58.917", "DRR2018-05-24_12:14:12.054",
                "LHM2018-05-24_12:18:20.125");
        when(fileReader.readFromFile("start.log")).thenReturn(streamFromStartLog);
        Stream<String> streamFromEndLog = Stream.of("DRR2018-05-24_12:15:24.067", "SVF2018-05-24_12:04:03.332",
                "LHM2018-05-24_12:19:32.585");
        when(fileReader.readFromFile("end.log")).thenReturn(streamFromEndLog);
    }

    @Test
    public void whenGetRacersBestTime_thenGetRacersBestTimeFromFile() {
        Map<String, Duration> expectedRacerBestTime = new HashMap<>();
        expectedRacerBestTime.put("DRR", Duration.parse("PT1M12.013S"));
        expectedRacerBestTime.put("SVF", Duration.parse("PT1M4.415S"));
        expectedRacerBestTime.put("LHM", Duration.parse("PT1M12.46S"));

        Map<String, Duration> actualRacerBestTime = racerTimeService.getRacersBestTime();

        assertEquals(expectedRacerBestTime, actualRacerBestTime);
    }
}
