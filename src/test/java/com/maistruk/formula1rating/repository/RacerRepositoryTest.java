package com.maistruk.formula1rating.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.maistruk.formula1rating.domain.Racer;
import com.maistruk.formula1rating.reader.FileReader;
import com.maistruk.formula1rating.repository.RacerRepository;
import com.maistruk.formula1rating.service.RacerTimeService;

public class RacerRepositoryTest {
    
    @Mock
    private FileReader fileReader;
    @Mock
    private RacerTimeService racerTimeService;
    @InjectMocks
    private RacerRepository racerRepository;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        MockitoAnnotations.initMocks(this);
//        fileReader = mock(FileReader.class);
//        racerTimeService = mock(RacerTimeService.class);
//        racerRepository = new RacerRepository(fileReader, racerTimeService);
        Stream<String> streamFromFile = Stream.of("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
                "SVF_Sebastian Vettel_FERRARI", "LHM_Lewis Hamilton_MERCEDES");
        when(fileReader.readFromFile("abbreviations.txt")).thenReturn(streamFromFile);
        Map<String, Duration> racersBestTime = new HashMap<>();
        racersBestTime.put("DRR", Duration.parse("PT1M12.013S"));
        racersBestTime.put("SVF", Duration.parse("PT1M4.415S"));
        racersBestTime.put("LHM", Duration.parse("PT1M12.46S"));
        when(racerTimeService.getRacersBestTime()).thenReturn(racersBestTime);
    }

    @Test
    public void whenGetRacers_thenGetRacersFromFile() {
        List<Racer> expectedRacers = new ArrayList<>();
        Racer racer1 = new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER");
        racer1.setBestLapTime(Duration.parse("PT1M12.013S"));
        expectedRacers.add(racer1);

        Racer racer2 = new Racer("SVF", "Sebastian Vettel", "FERRARI");
        racer2.setBestLapTime(Duration.parse("PT1M4.415S"));
        expectedRacers.add(racer2);

        Racer racer3 = new Racer("LHM", "Lewis Hamilton", "MERCEDES");
        racer3.setBestLapTime(Duration.parse("PT1M12.46S"));
        expectedRacers.add(racer3);

        List<Racer> actualRacers = racerRepository.getRacers();

        assertEquals(expectedRacers, actualRacers);
    }
}
