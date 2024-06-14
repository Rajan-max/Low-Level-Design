package DesignPatternPractice;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

enum LogLevel {
    INFO,
    DEBUG,
    WARN,
    ERROR
}

interface LogDestination {
    void log(LogMessage message);
}

class LogMessage {
    private final LogLevel level;
    private final String message;
    private final long timestamp;

    public LogMessage(LogLevel level, String message) {
        this.level = level;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

class ConsoleDestination implements LogDestination {
    @Override
    public void log(LogMessage message) {
        System.out.println(formatMessage(message));
    }

    private String formatMessage(LogMessage message) {
        return String.format("[%s] %s: %s", new java.util.Date(message.getTimestamp()), message.getLevel(), message.getMessage());
    }
}


class FileDestination implements LogDestination {
    private final String filePath;

    public FileDestination(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void log(LogMessage message) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(formatMessage(message) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatMessage(LogMessage message) {
        return String.format("[%s] %s: %s", new java.util.Date(message.getTimestamp()), message.getLevel(), message.getMessage());
    }
}


class Logger {
    private final List<LogDestination> destinations = new ArrayList<>();
    private LogLevel currentLevel = LogLevel.INFO;

    public void addDestination(LogDestination destination) {
        destinations.add(destination);
    }

    public void setLogLevel(LogLevel level) {
        this.currentLevel = level;
    }

    public void log(LogLevel level, String message) {
        if (level.ordinal() >= currentLevel.ordinal()) {
            LogMessage logMessage = new LogMessage(level, message);
            for (LogDestination destination : destinations) {
                destination.log(logMessage);
            }
        }
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
}


class LoggingFrameworkExample {
    public static void main(String[] args) {
        Logger logger = new Logger();

        // Configure destinations
        logger.addDestination(new ConsoleDestination());
        logger.addDestination(new FileDestination("app.log"));

        // Set log level
        logger.setLogLevel(LogLevel.DEBUG);

        // Log messages
        logger.info("This is an info message.");
        logger.debug("This is a debug message.");
        logger.warn("This is a warning message.");
        logger.error("This is an error message.");
    }
}

