package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static config.Utilities.URL;

public class HomePage {
    private WebDriver driver;
    private By upperOrderButton = By.className("Button_Button__ra12g");;
    private By lowerOrderButton = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[5]/button[1]");
    private By cookieButton = By.cssSelector(".App_CookieButton__3cvqF");
    private By homeHeader = By.className("Home_Header__iJKdX");
    private WebElement FAQuest;
    private WebElement FAQanswer;

    public HomePage (WebDriver driver) {

        this.driver = driver;
    }

    public HomePage navigateToHomePage() {
        driver.navigate().to(URL);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(homeHeader).getText() != null
                && !driver.findElement(homeHeader).getText().isEmpty()));
        return this;
    }

    public HomePage acceptCookies() {
        driver.findElement(cookieButton).isEnabled();
        driver.findElement(cookieButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return this;
    }

    public OrderPage pressUpperOrderButton() {
        driver.findElement(upperOrderButton).isEnabled();
        driver.findElement(upperOrderButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(OrderPage.getOrderForm()));
        return new OrderPage(driver);
    }

    public OrderPage pressLowerOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(lowerOrderButton));
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.elementToBeClickable(lowerOrderButton)).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOfElementLocated(OrderPage.getOrderForm()));
        return new OrderPage(driver);
    }

    public String getAnswerToFAQ(int FAQnum) {
        --FAQnum;
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                FAQuest = driver.findElement(By.id("accordion__heading-" + FAQnum)));

        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.elementToBeClickable(FAQuest)).click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-" + FAQnum)));

        FAQanswer = driver.findElement(By.id("accordion__panel-" + FAQnum));
        FAQanswer.isEnabled();
        String answer = FAQanswer.getText();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return answer;
    }
}
