package OrderFlowTests;

import extensions.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import static config.Utilities.*;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderFlowTest {
    private WebDriver driver;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String station;
    private final String phoneNumber;
    private final String calendarDate;
    private final String numberOfDays;
    private final String comment;
    private boolean isSubmitted;

    public OrderFlowTest(
            String firstName, String lastName,
            String address, String station, String phoneNumber,
            String calendarDate,
            String numberOfDays, String comment, boolean isSubmitted
    ) {
        this.firstName = firstName; //0
        this.lastName = lastName; //1
        this.address = address; //2
        this.station = station; //3
        this.phoneNumber = phoneNumber; //4
        this.calendarDate = calendarDate; //5
        this.numberOfDays = numberOfDays; //6
        this.comment = comment; //7
        this.isSubmitted = isSubmitted; //8
    }

    @Parameterized.Parameters(name = "{index} : Имя {0}, Фамилия {1}, Адрес {2}, Метро {3}, Телефон {4}, Дата {5} ," +
            "кол-во дней аренды {6}, Комментарий {7}, Должен ли разместиться успешно такой заказ? {8}")

    public static Object[][] setOrderParams() {
        return new Object[][]{
                {"Ваня", "Кошкин", "сивцев вражек 1 стр 1 д 1",
                        "Кропоткинская", "89764563423",
                        TOMORROW, "7", "побыстрее", true
                },
                {"Жан", "Петров", "Бутово 123456",
                        "Адмирала Ушакова", "89076785123",
                        TODAY, "1", "", false // Баг - на сегодня заказ сделать нельзя (см HomePage FAQ № 4)
                },
                {"Ана", "Смит", "Москва Тверская 1 стр 1 кв 1",
                        "Тверская", "0017896543221",
                        YESTERDAY, "6", "faster please", false // Баг - заказ принимается на прошлую дату
                },
                {"Ан", "Сэ", "",
                        "Охотный ряд", "89076785123",
                        NEXT_YEAR, "5", "" , false // Баг - поле адрес обязательное, но форма пропускает его незаполненным
                }
        };
    }

    @Before
    public void setUpDriver() {
        driver = WebDriverFactory.getDriver();
    }

    @Test
    public void checkOrderFlow() {
        boolean isOrderSuccessful = new HomePage(driver)
                .acceptCookies()
                .pressOrderButton()
                .fillInTextFields(firstName, lastName, address, phoneNumber)
                .selectMetroStation(station)
                .clickProceedButton()
                .inputDeliveryDate(calendarDate)
                .setRentalDuration(numberOfDays)
                .setGrayColorCheckBox()
                .leaveComment(comment)
                .pressSubmitButton()
                .confirmOrder()
                .isStatusButtonVisible();
        assertTrue(isOrderSuccessful == isSubmitted);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
