package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentalDetailsPage {
    private WebDriver driver;
    private WebElement colourCheckBox;
    private static final By orderHeader = By.cssSelector(".Order_Content__bmtHS .Order_Header__BZXOb");
    private final By deliveryDateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By monthSelect = By.xpath("//button[@aria-label='Next Month']");
    private final By rentalTermField = By.cssSelector(".Dropdown-control");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By submitButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By confirmationButton = By.xpath("//button[text()='Да']");


    public RentalDetailsPage (WebDriver driver) {
        this.driver = driver;
    }

    public RentalDetailsPage selectDeliveryDate(String calendarRow, String calendarColumn ) {
        By dateSelect = By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div["
                + calendarRow + "]/div[" + calendarColumn + "]");
        driver.findElement(deliveryDateInput).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(monthSelect).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement dateToPick = driver.findElement(dateSelect);
        dateToPick.isEnabled();
        dateToPick.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return this;
    }

    public RentalDetailsPage inputDeliveryDate(String calendarDate) {
        driver.findElement(deliveryDateInput).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(deliveryDateInput).sendKeys(calendarDate);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(deliveryDateInput).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return this;
    }

    public RentalDetailsPage setRentalDuration(String numberOfDays) {
        driver.findElement(rentalTermField).isEnabled();
        driver.findElement(rentalTermField).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebElement dropDownField = driver.findElement(By.xpath
                ("/html/body/div/div/div[2]/div[2]/div[2]/div[2]/div["+ numberOfDays +"]"));
        dropDownField.isEnabled();
        dropDownField.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return this;
    }

    public RentalDetailsPage setColour(String colour) {
        colourCheckBox = driver.findElement(By.id(colour));
        colourCheckBox.isEnabled();
        colourCheckBox.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return this;
    }

    public RentalDetailsPage leaveComment(String comment) {
        WebElement commentInputField = driver.findElement(commentInput);
        commentInputField.clear();
        commentInputField.sendKeys(comment);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return this;
    }

    public RentalDetailsPage pressSubmitButton() {
        driver.findElement(submitButton).isEnabled();
        driver.findElement(submitButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        return this;

    }

    public OrderSubmittedConfirmationPage confirmOrder() {
        driver.findElement(confirmationButton).isEnabled();
        driver.findElement(confirmationButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOfElementLocated(OrderSubmittedConfirmationPage.getStatusButton()));
        return new OrderSubmittedConfirmationPage(driver);
    }

    public static By getOrderHeader() {
        return orderHeader;
    }
}
