package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class Utilities {
        public static final String URL = "https://qa-scooter.praktikum-services.ru/";

        public void standardWait(WebDriver driver, int seconds) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        }

        public void setUpChromeBonigarcia(WebDriver driver) {
            WebDriverManager.chromedriver().setup();
            driver.manage().window().maximize();
        }

        public void setUpFireFoxBonigarcia(WebDriver driver) {
            WebDriverManager.firefoxdriver().setup();
            driver.manage().window().maximize();

        }
    }

