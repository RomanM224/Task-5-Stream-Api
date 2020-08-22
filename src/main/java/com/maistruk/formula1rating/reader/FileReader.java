package com.maistruk.formula1rating.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

    public Stream<String> readFromFile(String filePath){
        try {
            URL url = getClass().getClassLoader().getResource(filePath);
            if (url == null) {
                throw new FileNotFoundException("File not found: " + filePath);
            }
            return Files.lines(Paths.get(url.toURI()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.empty();
    }
}
