package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class WebUI {

    private static WebDriver driver;
    private static JavascriptExecutor jsExecutor;

    public static WebDriver openBrowser(String browserName) {
        return openBrowser(browserName, null);
    }

    public static WebDriver openBrowser(String browserName, String browserLink) {
        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                ExtentReport.statusPassed("Open Browser Chrome");
            } else if (browserName.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--start-maximized");
                driver = new FirefoxDriver(options);
                ExtentReport.statusPassed("Open Browser Firefox");
            } else if (browserName.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                driver = new EdgeDriver(options);
                ExtentReport.statusPassed("Open Browser Edge");
            } else if (browserName.equalsIgnoreCase("safari")) {
                WebDriverManager.safaridriver().setup();
                SafariOptions options = new SafariOptions();
                driver = new SafariDriver(options);
                ExtentReport.statusPassed("Open Browser Safari");
            } else {
                String message = "\nyou have not inputted the browser you want to use\nyou can input the browser you want to use like \"chrome\", \"firefox\" , \"edge\" or \"safari\"";
                ExtentReport.statusFailed(message);
                Assert.fail(message);
            }

            if (browserLink != null) {
                driver.get(browserLink);
            }
        } catch (Exception e) {
            Message.errorMessage("Failed Open Browser : "+e.getMessage());
            throw new AssertionError("Open Browser : "+e.getMessage());

        }
        return driver;
    }

    public static void closeBrowser() {
        try {
            if (driver != null) {
                driver.quit();
                ExtentReport.statusPassed("Close Browser");
            }
        } catch (Exception e) {
            Message.errorMessage("Failed Close Browser : "+e.getMessage());
            throw new AssertionError("Close Browser : "+e.getMessage());
        }

    }

    public static void goToUrl(String url) {

        try {
            if (url == null || url.trim().isEmpty()) {
                throw new IllegalArgumentException("URL cannot be empty or null.");
            }
            driver.get(url);
            ExtentReport.statusPassed("Go To URL " + url);
        } catch (Exception e) {
            Message.errorMessage("Failed Go To URL : "+e.getMessage());
            throw new AssertionError("Go To URL : "+e.getMessage());
        }

    }


    public static void back() {
        try {
            driver.navigate().back();
            ExtentReport.statusPassed("Back Browser");
        } catch (Exception e) {
            Message.errorMessage("Failed Back Browser : "+e.getMessage());
            throw new AssertionError("Back Browser: "+e.getMessage());
        }

    }

    //Success Testing
    public static void forward() {
        try {
            driver.navigate().forward();
            ExtentReport.statusPassed("Forward Browser");
        } catch (Exception e) {
            Message.errorMessage("Failed Forward Browser : "+e.getMessage());
            throw new AssertionError("Forward Browser : "+e.getMessage());
        }

    }

    public static void refresh() {
        try {
            driver.navigate().refresh();
            ExtentReport.statusPassed("Refresh Browser");
        } catch (Exception e) {
            throw new AssertionError("Refresh Browser : "+e.getMessage());
        }

    }

    public static void waitForElementPresent(By ObjectRepository, int Timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeout));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectRepository));
            ExtentReport.statusPassed("Element is present");
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            Message.errorMessage("Element is not present: " + e.getMessage());
            throw new AssertionError("Element is not present : "+e.getMessage());
        }
    }

    public static Boolean verifyElementPresent(By ObjectRepository, int Timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeout));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectRepository));
            ExtentReport.statusPassed("Verify Element is present");
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            Message.errorMessage("Failed Close Browser : "+e.getMessage());
            throw new AssertionError("Element Is Not Present : "+e.getMessage());
        }
    }

    public static void click(By ObjectRepository) {
        try {
            driver.findElement(ObjectRepository).click();
            ExtentReport.statusPassed("Click");
        } catch (NoSuchElementException | TimeoutException e) {
            throw new AssertionError("Click : "+e.getMessage());
        }
    }

    public static void delay(int TimeoutInSecond) {
        try {
            Thread.sleep(TimeoutInSecond*1000);
            ExtentReport.statusPassed("Delay at "+TimeoutInSecond+" Second");
        } catch (InterruptedException e) {
            Message.errorMessage("Failed Delay : "+e.getMessage());
            throw new AssertionError("Delay : "+e.getMessage());
        }
    }

    public static WebElement setText(By ObjectRepository,String Text) {
        try {
            WebElement element = driver.findElement(ObjectRepository);
            element.sendKeys(Text);
            ExtentReport.statusPassed("Set Text " +Text);
            return element;
        } catch (NoSuchElementException | TimeoutException e) {
            Message.errorMessage("Failed Set Text : "+e.getMessage());
            throw new AssertionError("Set Text : "+e.getMessage());
        }
    }

    public static WebElement setEncriptText(By ObjectRepository,String Text) {
        try {
            WebElement element = driver.findElement(ObjectRepository);
//            element.sendKeys(EncriptText.decrypt(Text));
            ExtentReport.statusPassed("Set Encript Text " +Text);
            return element;
        } catch (NoSuchElementException | TimeoutException e) {
            Message.errorMessage("Failed Set Encript Text : "+e.getMessage());
            throw new AssertionError("Set Encript Text : "+e.getMessage());
        }
    }

    public static WebElement clearText(By ObjectRepository) {
        try {
            WebElement element = driver.findElement(ObjectRepository);
            element.clear();
            ExtentReport.statusPassed("Clear Text");
            return element;
        } catch (NoSuchElementException | TimeoutException e) {
            Message.errorMessage("Failed Clear Text : "+e.getMessage());
            throw new AssertionError("Clear Text : "+e.getMessage());
        }
    }

    public static String getText(By ObjectRepository) {
        try {
            WebElement element = driver.findElement(ObjectRepository);
            ExtentReport.statusPassed("Get Text");
            return element.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            Message.errorMessage("Failed Get Text : "+e.getMessage());
            throw new AssertionError("Get Text : "+e.getMessage());
        }
    }

    public static WebElement scrollToElement(By ObjectRepository) {
        try {
            WebElement element = driver.findElement(ObjectRepository);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            ExtentReport.statusPassed("Scroll To Element");
            return element;
        } catch (NoSuchElementException | TimeoutException e) {
            Message.errorMessage("Failed Scroll To Element : "+e.getMessage());
            throw new AssertionError("Scroll To Element : "+e.getMessage());
        }
    }

    public static void scrollToPosition(int x, int y) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            String script = String.format("window.scrollTo(%d, %d);", x, y);
            jsExecutor.executeScript(script);
            ExtentReport.statusPassed("Scroll To Posiion "+"x : "+x+ " y : "+y);
        } catch (NoSuchElementException | TimeoutException e) {
            Message.errorMessage("Failed Scroll To Position : "+e.getMessage());
            throw new AssertionError("Scroll To Position : "+e.getMessage());
        }
    }

    public static void verifyMatch(String actualValue, String expectedValue) {
        try {
            if (actualValue.equals(expectedValue)) {
                ExtentReport.statusPassed("Verify Match");
            } else {
                String message = actualValue+" Not Match With " + expectedValue;
                ExtentReport.statusFailed(message);
                throw new AssertionError(message);
            }

        } catch (NoSuchElementException | TimeoutException e) {
            Message.errorMessage("Failed Verify Match : "+e.getMessage());
            throw new AssertionError("Verify Match : "+e.getMessage());
        }
    }

//    public static void TestDataDriven(String SquadName, String ExcelName, String SheetName, Consumer<Object[]> action) {
//        Object[][] testData = ExcelReader.getTestData(SquadName,ExcelName,SheetName);
//
//        for (Object[] header : testData) {
//            ExtentReport.startReport("Test Data Driven - "+SheetName);
//            action.accept(header);
//            closeBrowser();
//            ExtentReport.endReporting();
//        }
//    }



//    public static WebElement check(By ObjectRepository) {
//        //belum dilakukan pengetesan
//        WebElement checkbox = driver.findElement(ObjectRepository);
//        if (!checkbox.isSelected()) {
//            checkbox.click();
//        }
//        return checkbox;
//    }
//
//    public static WebElement unCheck(By ObjectRepository) {
//        //belum dilakukan pengetesan
//        WebElement checkbox = driver.findElement(ObjectRepository);
//        if (checkbox.isSelected()) {
//            checkbox.click();
//        }
//        return checkbox;
//    }

//    public static Alert alertAccept() {
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//        alert.accept();
//        return alert;
//    }
//
//    public static boolean verifyElementNotClickable(By ObjectRepository) {
//        try {
//            boolean value = driver.findElement(ObjectRepository).isEnabled();
//            System.out.println(value);
//            if (value == false) {
//                ExtentReport.statusPassed("Element Is Not Clickable\n" + value);
//            } else {
//                ExtentReport.statusFailed("Element Is Clickable\n" + value);
//                ExtentReport.endReporting();
//                throw new AssertionError("Failed Element Is Clickable");
//            }
//            return value;
//        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
//            String message = e.getMessage();
//            String errorMessage = "";
//
//            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//            if (stackTrace.length > 2) { // Mulai dari indeks 2 untuk melewati baris ini dan pemanggilannya
//                String className = stackTrace[2].getClassName(); // Nama kelas
//                String methodName = stackTrace[2].getMethodName(); // Nama metode
//                String fileName = stackTrace[2].getFileName(); // Nama file
//                int lineNumber = stackTrace[2].getLineNumber(); // Nomor baris
//                errorMessage += "at " + className + "." + methodName + "(" + fileName + ":" + lineNumber + ")";
//            }
//
//            ExtentReport.statusFailed(message+"<br><br>"+errorMessage);
//            ExtentReport.endReporting();
//            throw new AssertionError(message);
//        }
//    }
//
//    public static WebElement switchToFrame(By ObjectRepository) {
//        WebElement element = driver.findElement(ObjectRepository);
//        driver.switchTo().frame(element);
//        return element;
//    }
//
//    public static void switchToWindowsByIndex(int WindowsIndex) {
//        Set<String> windowsHandles = driver.getWindowHandles();
//        if (WindowsIndex >= 0 && WindowsIndex < windowsHandles.size()) {
//            String[] handles = windowsHandles.toArray(new String[0]);
//            driver.switchTo().window(handles[WindowsIndex]);
//        } else {
//            throw new IllegalArgumentException("Invalid Windows Index : "+WindowsIndex);
//        }
//    }
//
//    public static void switchToWindowsByTitle(String WindowsTitle) {
//        String currentWindowsHandle = driver.getWindowHandle();
//        Set<String> windowsHendle = driver.getWindowHandles();
//
//        for (String handle : windowsHendle) {
//            driver.switchTo().window(handle);
//            if (driver.getTitle().equals(WindowsTitle)); {
//                return;
//            }
//        }
//
//        driver.switchTo().window(currentWindowsHandle);
//        throw new IllegalArgumentException("Windows with title not found :" +WindowsTitle);
//    }
//
//    public static void switchToWindowsByUrl(String WindowsUrl) {
//        String currentWindowsHandle = driver.getWindowHandle();
//        Set<String> windowsHandle = driver.getWindowHandles();
//
//        for (String hanle : windowsHandle) {
//            driver.switchTo().window(hanle);
//            if (driver.getCurrentUrl().equals(WindowsUrl)) {
//                return;
//            }
//        }
//
//        driver.switchTo().window(currentWindowsHandle);
//        throw new IllegalArgumentException("Windows with URL not found : " + WindowsUrl);
//    }


}
