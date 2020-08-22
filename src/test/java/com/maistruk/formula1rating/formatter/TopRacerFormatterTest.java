package com.maistruk.formula1rating.formatter;

import static java.lang.System.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.maistruk.formula1rating.formatter.TopRacersFormatter;
import com.maistruk.formula1rating.domain.Racer;

public class TopRacerFormatterTest {

    private TopRacersFormatter topRacersFormatter;
    private List<Racer> racers;

    @BeforeEach
    public void setUp() {
        topRacersFormatter = new TopRacersFormatter();
        racers = new ArrayList<>();
        Racer racer1 = new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER");
        racer1.setBestLapTime(Duration.parse("PT1M12.013S"));
        racers.add(racer1);
        Racer racer2 = new Racer("SVF", "Sebastian Vettel", "FERRARI");
        racer2.setBestLapTime(Duration.parse("PT1M4.415S"));
        racers.add(racer2);
        Racer racer3 = new Racer("LHM", "Lewis Hamilton", "MERCEDES");
        racer3.setBestLapTime(Duration.parse("PT1M12.46S"));
        racers.add(racer3);
    }

    @Test
    public void givenRacersToTopRacersFormatter_whenFormatting_thenGetTableOfResults() {
        StringBuilder expected = new StringBuilder();
        expected.append("1.Sebastian Vettel     | FERRARI                        |01:04:415").append(lineSeparator())
                .append("2.Daniel Ricciardo     | RED BULL RACING TAG HEUER      |01:12:013").append(lineSeparator())
                .append("3.Lewis Hamilton       | MERCEDES                       |01:12:460").append(lineSeparator());

        String actual = topRacersFormatter.format(racers, 15);

        assertEquals(expected.toString(), actual);
    }

    @Test
    public void givenRacesToTopRacersFormatter_whenFormatting_thenGetTableOfResultsWithSeparativeLine() {
        StringBuilder expected = new StringBuilder();
        expected.append("1.Sebastian Vettel     | FERRARI                        |01:04:415").append(lineSeparator())
                .append("2.Daniel Ricciardo     | RED BULL RACING TAG HEUER      |01:12:013").append(lineSeparator())
                .append("------------------------------------------------------------------").append(lineSeparator())
                .append("3.Lewis Hamilton       | MERCEDES                       |01:12:460").append(lineSeparator());

        String actual = topRacersFormatter.format(racers, 2);

        assertEquals(expected.toString(), actual);
    }
}
