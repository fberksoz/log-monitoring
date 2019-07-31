package com.demo.logmonitoring.utils;

import com.demo.logmonitoring.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.regex.Pattern;

public class LogScanner implements Runnable {
    Path logFile = Paths.get("app.log");
    private int lines = 0;
    private int characters = 0;

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    public static void main(String[] args)
    {
        new LogScanner().run();
    }

    public void run()
    {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();

            try (BufferedReader in = new BufferedReader(new FileReader(logFile.toFile()))) {
                String line = "";
                while (true) {
                    try {
                        if (!((line = in.readLine()) != null)) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lines++;
                    characters += line.length() + System.lineSeparator().length();
                }
            }

            logFile.toAbsolutePath().getParent().register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);

            do {
                WatchKey key = watcher.take();
                System.out.println("Waiting...");
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;
                    Path path = pathEvent.context();
                    if (path.equals(logFile)) {
                        try (BufferedReader in = new BufferedReader(new FileReader(pathEvent.context().toFile()))) {
                            String line;
                            in.skip(characters);
                            while ((line = in.readLine()) != null) {
                                lines++;
                                characters += line.length() + System.lineSeparator().length();

                                System.out.println(line);

                            }
                        }
                    }
                }
                key.reset();
            } while (true);
        } catch (IOException | InterruptedException ex) {
            logger.error("[LogScanner] Error reading log file: ", ex);
        }
    }
}
