package main;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class FileParser {
    public List<String> parse(String fileName) {
        URI resource;
        try {
            resource = FileParser.class.getClassLoader().getResource(fileName).toURI();
            File file = new File(resource);
            return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }

    public FileParser(Class<?> aClass) {
    }
}
