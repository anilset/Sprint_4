package OrderFlowTests;

import config.Utilities;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HomePage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)

public class OrderFlowTest1 {

    private WebDriver driver;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String station;
    private final String phoneNumber;
    private final String calendarRow;
    private final String calendarColumn;
    private final String numberOfDays;
    private final String comment;
    private boolean isOrderSubmitted;

    //В этом тесте вход с нижней кнопки, выбор даты по пересечению (календарь - матрица 5х7)

    public OrderFlowTest1(
            String firstName, String lastName,
            String address, String station, String phoneNumber,
            String calendarRow, String calendarColumn,
            String numberOfDays, String commentInput,
            boolean isOrderSubmitted
    ) {
        this.firstName = firstName; //0
        this.lastName = lastName;   //1
        this.address = address;     //2
        this.station = station;     //3
        this.phoneNumber = phoneNumber; //4
        this.calendarRow = calendarRow; //5
        this.calendarColumn = calendarColumn; //6
        this.numberOfDays = numberOfDays; //7
        this.comment = commentInput; //8
        this.isOrderSubmitted = isOrderSubmitted; //9
    }

    @Parameterized.Parameters (name = "{index} : Имя {0}, Фамилия {1}, Адрес {2}, Метро {3}, Телефон {4}, Ряд календаря {5}, Колонка календаря {6} ," +
            "кол-во дней аренды {7}, Комментарий {8}, Размещен ли успешно заказ? {9}")

    public static Object[][] setOrderParams() {
        return new Object[][]{
                {"Ян", "Ли", "Москва, пр-кт Вернадского, 47 к.4, кв. 75",
                        "Проспект Вернадского", "+79876543221",
                        "1", "1", "3", "не звонить", true
                }, // проходит успешно
                {"Жан-Жак", "Петров-Водкин", "Бутово, изюмская 5 23",
                        "Улица Скобелевская", "89076785678",
                        "5", "7", "6", "", true
                }, // падает - поля ввода имени и фамилии не принимают дефис
                {"Anna", "Smith", "Moscow",
                        "Тверская", "+17896543221",
                        "3", "4", "1", "", true
                } // падает - поля ввода имени и фамилии не принимают латиницу
        };
    }

    @Test
    public void checkOrderFlowFF() {
        driver = new FirefoxDriver();
        new Utilities().setUpFireFoxBonigarcia(driver);
        boolean isOrderSuccessful = new HomePage(driver)
                .navigateToHomePage()
                .acceptCookies()
                .pressUpperOrderButton() //зашли через верхнюю кнопку
                .fillInTextFields(firstName, lastName, address, phoneNumber)
                .selectMetroStation(station)
                .clickProceedButton()
                .selectDeliveryDate(calendarRow, calendarColumn)
                .setRentalDuration(numberOfDays)
                .setBlackColorCheckBox()
                .leaveComment(comment)
                .pressSubmitButton()
                .confirmOrder()
                .isStatusButtonVisible();
        assertTrue (isOrderSubmitted == isOrderSuccessful);

    }

    @Test
    public void checkOrderFlowChrome() { //в хроме все тесты падают по условиям
        driver = new ChromeDriver();
        new Utilities().setUpChromeBonigarcia(driver);
        boolean isOrderSuccessful = new HomePage(driver)
                .navigateToHomePage()
                .acceptCookies()
                .pressUpperOrderButton() //зашли через верхнюю кнопку
                .fillInTextFields(firstName, lastName, address, phoneNumber)
                .selectMetroStation(station)
                .clickProceedButton()
                .selectDeliveryDate(calendarRow, calendarColumn)
                .setRentalDuration(numberOfDays)
                .setBlackColorCheckBox()
                .leaveComment(comment)
                .pressSubmitButton()
                .confirmOrder()
                .isStatusButtonVisible();
        assertTrue (isOrderSubmitted == isOrderSuccessful);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    //аннотация не работает нормально c FireFox.
    // также, никак не поучилось настроить Setup через WebDriverFactory для параметризванных тестов
}
