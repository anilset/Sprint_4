package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.time.LocalDate;

public class Utilities {
        public static final String URL = "https://qa-scooter.praktikum-services.ru/";
        public static final LocalDate CURRENT_DATE = LocalDate.now();
        public static final String TODAY = String.valueOf(CURRENT_DATE);
        public static final String TOMORROW = String.valueOf(CURRENT_DATE.plusDays(1));
        public static final String YESTERDAY = String.valueOf(CURRENT_DATE.minusDays(1));

        public static final String NEXT_MONTH = String.valueOf(CURRENT_DATE.plusMonths(1));

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

