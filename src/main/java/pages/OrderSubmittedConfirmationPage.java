package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class OrderSubmittedConfirmationPage {
    private final WebDriver driver;
    public static final By viewStatusButton = By.xpath("//button[text()='Посмотреть статус']");

    public OrderSubmittedConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderSubmittedConfirmationPage clickStatusButton() {
        driver.findElement(viewStatusButton).isEnabled();
        driver.findElement(viewStatusButton).click();
        return new OrderSubmittedConfirmationPage(driver);
    }

    public boolean isStatusButtonVisible() {
        List<WebElement> buttons = driver.findElements(viewStatusButton);
        return buttons.size() != 0;
    }
}
