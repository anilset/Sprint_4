package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static pages.OrderPage.orderForm;

public class HomePage {
    private final WebDriver driver;
    private WebElement FAQuest;
    public static By homeHeader = By.className("Home_Header__iJKdX");
    private final By orderButton = By.cssSelector(".Button_Button__ra12g");
    private final By cookieButton = By.cssSelector(".App_CookieButton__3cvqF");
    private final By accordionQuestButton = By.className("accordion__button");

    public HomePage (WebDriver driver) {

        this.driver = driver;
    }

    public HomePage acceptCookies() {
        driver.findElement(cookieButton).isEnabled();
        driver.findElement(cookieButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return this;
    }

    public OrderPage pressOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButton));
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.elementToBeClickable(orderButton)).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOfElementLocated(orderForm));
        return new OrderPage(driver);
    }

    public String getAnswerToFAQ(int FAQnum) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                FAQuest = driver.findElement(By.id("accordion__heading-" + FAQnum)));

        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.elementToBeClickable(FAQuest)).click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-" + FAQnum)));

        WebElement FAQanswer = driver.findElement(By.id("accordion__panel-" + FAQnum));
        FAQanswer.isEnabled();
        String answer = FAQanswer.getText();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return answer;
    }

    public By getAccordionQuestButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.className("accordion__button")));
        return accordionQuestButton;
    }
}
