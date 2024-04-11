package DesignPatternPractice;

abstract class LogProcessor {

    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    LogProcessor nextLoggerProcessor;

    LogProcessor(LogProcessor loggerProcessor) {

        this.nextLoggerProcessor = loggerProcessor;

    }

    public void log(int logLevel, String message) {

        if (nextLoggerProcessor != null) {
            nextLoggerProcessor.log(logLevel, message);
        }
    }
}

class DebugLogProcessor extends LogProcessor {

    DebugLogProcessor(LogProcessor nexLogProcessor) {
        super(nexLogProcessor);
    }

    public void log(int logLevel, String message) {

        if (logLevel == DEBUG) {
            System.out.println("DEBUG: " + message);
        } else {

            super.log(logLevel, message);
        }

    }
}

class ErrorLogProcessor extends LogProcessor {

    ErrorLogProcessor(LogProcessor nexLogProcessor) {
        super(nexLogProcessor);
    }

    public void log(int logLevel, String message) {

        if (logLevel == ERROR) {
            System.out.println("ERROR: " + message);
        } else {

            super.log(logLevel, message);
        }

    }
}

class InfoLogProcessor extends LogProcessor {

    InfoLogProcessor(LogProcessor nexLogProcessor) {
        super(nexLogProcessor);
    }

    public void log(int logLevel, String message) {

        if (logLevel == INFO) {
            System.out.println("INFO: " + message);
        } else {

            super.log(logLevel, message);
        }

    }
}


public class LoggerServiceChainOfResp {
    public static void main(String[] args) {
        LogProcessor logObject = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));

        logObject.log(LogProcessor.ERROR, "exception happens");
        logObject.log(LogProcessor.DEBUG, "need to debug this ");
        logObject.log(LogProcessor.INFO, "just for info ");


    }
}
