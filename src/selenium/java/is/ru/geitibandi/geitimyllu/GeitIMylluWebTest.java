package is.ru.geitibandi.geitimyllu;

import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;

public class GeitIMylluWebTest {

    static WebDriver driver;

    @BeforeClass
    public static void before() {
        // Will be run before all tests in class are run
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void after() {
        // Will be run after all tests in class have run
        driver.close();
    }

    @Before
    public void setup() {
        // Will be run before each test in class is run
        ;
    }

    @Test
    public void assertNameInJoke() {
        String port = System.getenv("PORT");
        if(port == null){
            port = "4567";
        }
        driver.get("http://localhost:" + port);
        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertTrue(bodyText.toLowerCase().contains("chuck norris"));
    }
}