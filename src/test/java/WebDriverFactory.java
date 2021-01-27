import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class WebDriverFactory {
    static public WebDriver create(String webDriverName){
        switch (webDriverName.toLowerCase()){
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
            case "opera":
                return new OperaDriver();
            case "ie":
                return new InternetExplorerDriver();
            default:
                throw  new RuntimeException("Некоректное имя браузера");
        }
    }
    static public WebDriver create(String webDriverName, ChromeOptions options){
        switch (webDriverName.toLowerCase()){
            case "chrome":
                return new ChromeDriver(options);
            default:
                throw  new RuntimeException("Некоректное имя браузера");
        }
    }
    static public WebDriver create(String webDriverName, EdgeOptions options){
        switch (webDriverName.toLowerCase()){
            case "edge":
                return new EdgeDriver(options);
            default:
                throw  new RuntimeException("Некоректное имя браузера");
        }
    }
    static public WebDriver create(String webDriverName, OperaOptions options){
        switch (webDriverName.toLowerCase()){
            case "opera":
                return new OperaDriver(options);
            default:
                throw  new RuntimeException("Некоректное имя браузера");
        }
    }
    static public WebDriver create(String webDriverName, InternetExplorerOptions options){
        switch (webDriverName.toLowerCase()){
            case "ie":
                return new InternetExplorerDriver(options);
            default:
                throw  new RuntimeException("Некоректное имя браузера");
        }
    }
}
