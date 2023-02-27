package pages;

import extensions.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class HomePageFAQTest {

    private final String answer;
    private final int numOfQuest;
    private WebDriver driver;

    public HomePageFAQTest(String answer, int numOfQuest) {
        this.answer = answer;
        this.numOfQuest = numOfQuest;
    }

    @Parameterized.Parameters

    public static Object[][] getFAQTestData() {
        return new Object[][] {
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                        "можете просто сделать несколько заказов — один за другим.", 1},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в " +
                        "течение дня. Отсчёт времени аренды начинается с момента, " +
                        "когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, " +
                        "суточная аренда закончится 9 мая в 20:30.", 2},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку " +
                        "по красивому номеру 1010.", 4},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — " +
                        "даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"Да, пока самокат не привезли. " +
                        "Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7}
        };
    }

    @Before
    public void setUpDriver() {
        driver = WebDriverFactory.getDriver();
    }

    @Test
    public void compareAnswersFieldsText2() {

        HomePage homePage = new HomePage(driver);
        List<WebElement> accordionItems = driver.findElements(homePage.getAccordionQuestButton());
        for (WebElement accordionItem : accordionItems) {
            assertEquals(answer, homePage.getAnswerToFAQ(numOfQuest));
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}