package com.duallab.verapdf.fw;

import com.duallab.verapdf.tools.PropertiesValue;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class SikuliHelper {

    private static final String FILELOCATION = "\\src\\test\\java\\com\\duallab\\integr8tor\\imagePatterns";
    private static final String CAPTURESCREEN = "\\target\\captured-screens";
    private static final int X = Integer.parseInt(PropertiesValue.getRegionScreenDimensions()[0]);
    private static final int Y = Integer.parseInt(PropertiesValue.getRegionScreenDimensions()[1]);
    private static final int W = Integer.parseInt(PropertiesValue.getRegionScreenDimensions()[2]);
    private static final int H = Integer.parseInt(PropertiesValue.getRegionScreenDimensions()[3]);
    private static Logger log = Logger.getLogger(SikuliHelper.class.getName());

    public static String savePdfFile(String fileName) throws IOException, FindFailed, InterruptedException {
        log.info("Starting to handleWindowsPopUp ... " + fileName);

        String datafiles = (new java.io.File(".")).getCanonicalPath() + "\\target\\";
        String filepath = (new java.io.File(".")).getCanonicalPath()
                + FILELOCATION;

        Screen s = new Screen();
        log.info("Creating Pattern ...");
        log.info("Screen: " + s.toString());
        Pattern imagePattern = new Pattern(filepath + "\\" + "pdf-viewer\\File name.jpg");
        imagePattern.similar(PropertiesValue.getSimilar());

        log.info("handling Windows ...");
        s.wait(imagePattern, 30);
        log.info("s.getLastMatch().getScore() " + s.getLastMatch().getScore());

        log.info("Typing ..." + datafiles + fileName);
        s.type(imagePattern, datafiles + fileName);

        TimeUnit.MILLISECONDS.sleep(1000);

        clickOnImage("pdf-viewer\\save.jpg", PropertiesValue.getSimilar() * 0.9);
        log.info("Done.");
        return datafiles + fileName;
    }

    public static Match getMatchScreen(String fileName) throws FindFailed, IOException, InterruptedException {
        log.info("getMatchScreen starting ...");
        String str = (new java.io.File(".")).getCanonicalPath()
                + FILELOCATION + fileName;
        log.info("Creating Screen ...");
        Screen s = new Screen();
        s.setThrowException(false);
        log.info("Screen: " + s.toString());
        log.info("Waiting a little ... ");
        TimeUnit.MILLISECONDS.sleep(1000);

        log.info("Trying to find 'Match': ");
        Match m = s.find(str);
        log.info("Done.");
        return m;
    }

    public static Screen getMatchScreenWithSimilar(String fileName, double similar)
            throws IOException, FindFailed, InterruptedException {
        log.info("Starting with ... " + fileName);
        String filepath = (new java.io.File(".")).getCanonicalPath()
                + FILELOCATION;

        Screen s = new Screen();
        log.info("Screen: " + s.toString());
        log.info("s.getBounds() " + s.getBounds());
        log.info("Creating Pattern ...");
        Pattern imagePattern = new Pattern(filepath + "\\" + fileName);
        imagePattern.similar(similar);

        try {
            log.info("Waiting ..." + Runtime.getRuntime().getClass().getEnclosingMethod().toString());
            s.wait(imagePattern, 30);
            log.info("getScore: " + s.getLastMatch().getScore());
            log.info("Done.");
        } catch (Exception e) {
            log.info("Getting screen , ..." + e.toString());
            getScreen();
            TimeUnit.SECONDS.sleep(2);
            log.info("Trying again ... ");
            s.wait(imagePattern, 30);
            log.info("getScore: " + s.getLastMatch().getScore());
            log.info("Done twice.");
        }
        return s;
    }

    public static void clickOnImage(String fileName, double similar)
            throws IOException, FindFailed, InterruptedException {

        TimeUnit.SECONDS.sleep(1);

        try {
            log.info("Starting Image clicking ... " + fileName);
            Pattern imagePattern = createImagePattern(fileName, similar);
            log.info("Waiting ...");
            clickOnRegion(imagePattern);
            log.info("Done.");
        } catch (FindFailed e) {
            log.info("Nothing, trying again ..." + e.toString());
            TimeUnit.SECONDS.sleep(2);
            log.info("Starting Image clicking ... " + fileName);
            Pattern imagePattern = createImagePattern(fileName, similar);
            log.info("Waiting ...");
            clickOnRegion(imagePattern);
            log.info("Done twice.");
        }
        TimeUnit.SECONDS.sleep(1);
    }

    public static Pattern createImagePattern(String fileName, double similar) throws IOException {
        log.info("Starting ... ");
        String filepath = (new java.io.File(".")).getCanonicalPath()
                + FILELOCATION;

        log.info("Creating Pattern ...");
        Pattern imagePattern = new Pattern(filepath + "\\" + fileName);
        imagePattern.similar(similar);
        log.info("Done.");
        return imagePattern;
    }

    public static Match checkImage(String fileName, double similar)
            throws IOException, FindFailed, InterruptedException {
        Match m = null;
        log.info("Starting Image checking ... " + fileName);

        m = getImageDetails(fileName, similar);
        m.mouseMove(0, -300);
        log.info("Done.");
        return m;
    }

    public static Match getImageDetails(String fileName, double similar)
            throws FindFailed, InterruptedException, IOException {
        log.info("Starting ... ");
        Match m = null;

        try {
            Pattern imagePattern = createImagePattern(fileName, similar);

            log.info("Waiting ...");
            TimeUnit.SECONDS.sleep(3);
            Region r = waitImagePattern(imagePattern);
            m = r.find(imagePattern);
            log.info("m.getScore()" + m.getScore());
            log.info("Done.");
        } catch (FindFailed e) {
            log.info("Nothing, trying again ..." + e.toString());
            log.info("Getting screen , ..." + e.toString());
            getScreen();

            Pattern imagePattern = createImagePattern(fileName, similar);

            log.info("Waiting ...");
            TimeUnit.SECONDS.sleep(3);
            Region r = waitImagePattern(imagePattern);
            m = r.find(imagePattern);
            log.info("m.getScore()" + m.getScore());
            log.info("Done twice.");
        }
        return m;
    }

    public static void chooseAreaAndZoom(Pattern imagePattern) throws FindFailed {
        Region r = new Region(X, Y, W, H);
        log.info("r.getRect(): " + r.getRect());
        r.wait(imagePattern).highlight(0.1);
        r.find(imagePattern).click();
        r.mouseDown(16);
        r.mouseMove(150, 200);
        r.mouseUp(16);
        log.info("Done.");
    }

    public static void selectToMeasure(Pattern firstImagePattern, Pattern secondImagefirstImagePattern)
            throws FindFailed, InterruptedException {
        log.info("Trying to select ... ");
        Region r = new Region(X, Y, W, H);
        r.mouseMove(firstImagePattern);
        r.mouseDown(16);
        r.mouseMove(secondImagefirstImagePattern);
        r.mouseUp();
        r.mouseMove(-200, -200);
        TimeUnit.SECONDS.sleep(1);
        log.info("Done.");
    }

    public static void mouseMoveTo(String fileName, double similar)
            throws FindFailed, InterruptedException, IOException {
        log.info("Starting ... ");
        Region r = new Region(X, Y, W, H);
        Pattern imagePattern = createImagePattern(fileName, similar);
        log.info("Waiting ...");
        TimeUnit.SECONDS.sleep(1);
        r.mouseMove(imagePattern);
        TimeUnit.SECONDS.sleep(3);
        log.info("Done.");
    }

    public static void mouseMoveAside(int x, int y)
            throws InterruptedException {
        log.info("Starting ... ");
        Region r = new Region(X, Y, W, H);
        log.info("Waiting ...");
        TimeUnit.SECONDS.sleep(1);
        r.mouseMove(x, y);
        TimeUnit.SECONDS.sleep(1);
        log.info("Done.");
    }

    public static void getScreen() throws IOException {
        log.info("Starting getScreen() ... ");
        Screen s = new Screen();
        String captured_screensDir = Paths.get((new java.io.File(".")).getCanonicalPath() + CAPTURESCREEN + "\\")
                .toString();

        log.info("captured_screensDir: " + captured_screensDir);
        Files.createDirectories(Paths.get(captured_screensDir));
        File f = new File(captured_screensDir,
                "\\" + getCallerMethodName() + "_" + (new SimpleDateFormat("dd-MM-yy_HH-mm-SS").format(new Date())
                        + "_screen.jpg"));
        f.createNewFile();
        ImageIO.write(s.getScreen().capture().getImage(), "jpg", f);
    }

    private static void clickOnRegion(Pattern imagePattern) throws FindFailed, InterruptedException, IOException {
        Region r = waitImagePattern(imagePattern);
        r.find(imagePattern).click();
        log.info("getScore()" + r.getLastMatch().getScore());
    }

    private static Region waitImagePattern(Pattern imagePattern) throws FindFailed, InterruptedException, IOException {
        Region r = new Region(X, Y, W, H);
        log.info("r.getRect(): " + r.getRect());
        try {
            r.wait(imagePattern, 15).highlight(0.1);
            log.info("m.getScore()" + r.getLastMatch().getScore());
        } catch (Exception e) {
            log.info("Nothing, ..." + e.toString());
            getScreen();
            TimeUnit.SECONDS.sleep(2);
            log.info("Trying again to wait ..." + e.toString());
            r.wait(imagePattern, 15).highlight(0.1);
            log.info("m.getScore()" + r.getLastMatch().getScore());
            log.info("Done twice.");
        }
        return r;
    }

    private static String getCallerMethodName() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        return stackTraceElements[6].getMethodName() + stackTraceElements[5].getMethodName();
    }
}
