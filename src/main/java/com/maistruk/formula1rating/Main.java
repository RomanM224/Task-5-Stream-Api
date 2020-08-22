package com.maistruk.formula1rating;

import com.maistruk.formula1rating.formatter.TopRacersFormatter;
import com.maistruk.formula1rating.reader.FileReader;
import com.maistruk.formula1rating.repository.RacerRepository;
import com.maistruk.formula1rating.service.RacerTimeService;

public class Main {

    public static void main(String[] args) {
        int topRacersNumber = 15;
        FileReader fileReader = new FileReader();
        RacerTimeService racerTimeService = new RacerTimeService(fileReader);
        RacerRepository racerRepository = new RacerRepository(fileReader, racerTimeService);
        TopRacersFormatter topRacersFormatter = new TopRacersFormatter();
        System.out.println(topRacersFormatter.format(racerRepository.getRacers(), topRacersNumber));
    }
}
