import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromiumDriverManager;
import net.bytebuddy.asm.Advice;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import config.ServerConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

public class SampleTest {
    ChromeOptions options = new ChromeOptions();
    WebDriver driver;
    Logger logger = LogManager.getLogger(SampleTest.class);
    ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @BeforeTest
    public void setUp(){
        options.addArguments("headless");

        WebDriverManager.chromedriver().setup();
        driver = WebDriverFactory.create("chrome");

        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        logger.info("Драйвер поднят");
    }

    @Test
    public void openPageYa(){
        logger.info("Тест запущен");

        driver.get("https://yandex.ru");

        assertEquals(driver.getTitle(), "Яндекс");

        logger.info("Тест завершен");
    }

    By input = By.xpath("//input[@id = 'searchNumber']");

    @Test
    public void openPageTele2(){
        logger.info("Тест запущен");
        try {
            driver.get("https://msk.tele2.ru/shop/number");
        }
        catch (Exception e){
            new WebDriverWait(driver, 5).
                    until(ExpectedConditions.visibilityOfElementLocated(input));
        }

        WebElement inputElement = driver.findElement(input);
        inputElement.sendKeys("97");
        inputElement.sendKeys(Keys.ENTER);

        new WebDriverWait(driver, 5).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = '977']")));

        logger.info("Тест завершен");
    }

    @AfterTest
    public void setDown(){
        if (driver != null){
            driver.quit();
        }
        logger.info("Драйвер Закрыт");
    }


}
