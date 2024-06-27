package ru.gpbf.middle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtil {

    public static String readFileAsString(String file)
    {
        try {
            return new String(Files.readAllBytes(Paths.get(file).toAbsolutePath()));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
