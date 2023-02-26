package pages;

import Extensions.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

public class HomePageFAQTest {

    private WebDriver driver;

    @Before
    public void setUpDriver() {
        driver = WebDriverFactory.getDriver();
    }

    @Test
    public void compareAnswersFieldsText1() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        assertEquals("Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                homePage.getAnswerToFAQ(1));
    }

    @Test
    public void compareAnswersFieldsText2() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        assertEquals("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                        "можете просто сделать несколько заказов — один за другим.",
                homePage.getAnswerToFAQ(2));
    }

    @Test
    public void compareAnswersFieldsText3() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        assertEquals("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в " +
                        "течение дня. Отсчёт времени аренды начинается с момента, " +
                        "когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, " +
                        "суточная аренда закончится 9 мая в 20:30.",
                homePage.getAnswerToFAQ(3));
    }

    @Test
    public void compareAnswersFieldsText4() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        assertEquals("Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                homePage.getAnswerToFAQ(4));

    }

    @Test
    public void compareAnswersFieldsText5() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        assertEquals("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку " +
                        "по красивому номеру 1010.",
                homePage.getAnswerToFAQ(5));
    }

    @Test
    public void compareAnswersFieldsText6() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        assertEquals("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — " +
                        "даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                homePage.getAnswerToFAQ(6));
    }

    @Test
    public void compareAnswersFieldsText7() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        assertEquals("Да, пока самокат не привезли. " +
                        "Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                homePage.getAnswerToFAQ(7));
    }

    @Test
    public void compareAnswersFieldsText8() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        assertEquals("Да, обязательно. Всем самокатов! И Москве, и Московской области.",
                homePage.getAnswerToFAQ(8));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}