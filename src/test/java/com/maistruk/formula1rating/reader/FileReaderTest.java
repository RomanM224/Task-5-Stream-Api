package com.maistruk.formula1rating.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.maistruk.formula1rating.reader.FileReader;

public class FileReaderTest {

    private FileReader fileReader;

    @BeforeEach
    public void setUp() {
        fileReader = new FileReader();
    }

    @Test
    public void givenFilePath_whenReadingFromFile_thenGetStreamOfStrings() throws FileNotFoundException {
        List<String> expectedList = Arrays.asList("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
                "SVF_Sebastian Vettel_FERRARI", "LHM_Lewis Hamilton_MERCEDES");
        String filePath = "abbreviations.txt";

        Stream<String> actual = fileReader.readFromFile(filePath);
        List<String> actualList = actual.collect(Collectors.toList());

        assertEquals(expectedList, actualList);
    }
}
