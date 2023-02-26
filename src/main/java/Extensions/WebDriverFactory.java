package Extensions;

import config.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static config.Utilities.URL;


    public class WebDriverFactory {

        public static WebDriver getDriver() {
            String browserName = System.getenv().get("browser");
            WebDriver driver;
            switch (browserName) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                default: throw new RuntimeException("This browser is not supported yet");
            }
            Utilities util = new Utilities();
            util.standardWait(driver, 3);
            driver.get(URL);
            util.standardWait(driver, 3);
            driver.manage().window().maximize();
            util.standardWait(driver, 3);
            return driver;
        }
    }
