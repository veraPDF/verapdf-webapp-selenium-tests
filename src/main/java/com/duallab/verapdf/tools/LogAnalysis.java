package com.duallab.verapdf.tools;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class LogAnalysis {
    private static Logger log = Logger.getLogger(LogAnalysis.class.getName());

    public static Set<String> analyzeLog(WebDriver driver, String context) {


        Set<String> errorsSet = new HashSet<>();

        // creates pattern layout
        PatternLayout layout = new PatternLayout();
        String conversionPattern = " %-5p %c{2}:%L - %m%n";
        layout.setConversionPattern(conversionPattern);

        // creates file appender
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile("./target/tests_browser_console.log");
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();

        Logger logger = Logger.getLogger(context);

        logger.addAppender(fileAppender);

        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            errorsSet.add(entry.getLevel().toString());
            logger.info(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
        log.info("Number of error levels in BROWSER console:  " + errorsSet.size() + "\n");
        return errorsSet;
    }
}
