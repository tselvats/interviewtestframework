package com.Interview.web.util;

import com.Interview.core.util.PropertyUtils;
import com.Interview.core.util.logging.SimpliLogger;
import com.Interview.web.util.webdriver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.util.ImageTool;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpliScreenshot {

    private SimpliScreenshot() {
        throw new IllegalStateException("SimpliScreenshot");
    }

    private static final String OUTPUT_DIRECTORY = PropertyUtils.getPropertyString("com.Interview.web.screenshot.outputdir");

    @Attachment(value = "Screenshot: {0}", type = "image/png")
    public static byte[] captureViewport(String fileName) {

        WebDriver driver = DriverManager.getDriver();
        Screenshot shot = new AShot().shootingStrategy(ShootingStrategies.simple()).takeScreenshot(driver);
        saveScreenshotFile(shot, fileName);
        return screenshotToByteArray(shot);
    }

    @Attachment(value = "Screenshot: {0}", type = "image/png")
    public static byte[] captureEntirePage(String fileName) {
        WebDriver driver = DriverManager.getDriver();
        Screenshot shot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        saveScreenshotFile(shot, fileName);

        // Scroll to top of page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");

        return screenshotToByteArray(shot);
    }


    // PRIVATE METHODS ----------


    private static void saveScreenshotFile(Screenshot shot, String fileName){
        try {
            // Build output filename
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String now = LocalDateTime.now().format(formatter);
            String outputFileName = OUTPUT_DIRECTORY + createValidFilename(fileName) + "_" + now + ".png";
            File destFile = new File(outputFileName);

            // Copy to destination file
            destFile.mkdirs();
            ImageIO.write(shot.getImage(),"PNG", destFile);

            SimpliLogger.debug("SimpliScreenshot.saveScreenshot: Captured as: " + outputFileName+now);
        }
        catch (Exception e) {
            throw new RuntimeException("SimpliScreenshot.saveScreenshot: Cannot create screenshot", e);
        }
    }

    private static byte[] screenshotToByteArray(Screenshot shot){
        try {
            return ImageTool.toByteArray(shot);
        } catch (IOException e) {
            throw new RuntimeException("SimpliScreenshot.convertToByteArray: Cannot convert screenshot to byte array", e);
        }
    }

    private static String createValidFilename(String inputName){
        return inputName.replaceAll("[^a-zA-Z0-9.\\-]", "_");
    }
}
