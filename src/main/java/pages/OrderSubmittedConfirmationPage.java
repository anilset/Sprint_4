package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class OrderSubmittedConfirmationPage {
    private WebDriver driver;
    private static By viewStatusButton = By.xpath("//button[text()='Посмотреть статус']");

    public OrderSubmittedConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderSubmittedConfirmationPage clickStatusButton() {
        driver.findElement(viewStatusButton).isEnabled();
        driver.findElement(viewStatusButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return new OrderSubmittedConfirmationPage(driver);
    }


    public static By getStatusButton() {
        return viewStatusButton;
    }

    public boolean isStatusButtonVisible() {
        List<WebElement> buttons = driver.findElements(viewStatusButton);
        return buttons.size() != 0;
    }
}
