import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EdgeBrowserTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Set the path of  EdgeDriver
        System.setProperty("webdriver.edge.driver", "C:\\chengcoding\\Java\\edgedriver_win32\\msedgedriver.exe");
        // Create EdgeDriver instance
        driver = new EdgeDriver();
    }

    @Test
    public void testOpenBing() {
        //  websites
        driver.get("https://www.bing.com");

        String pageTitle = driver.getTitle();

        assertTrue(pageTitle.contains("Microsoft Bing"));
    }

    @AfterEach
    public void tearDown() {
        // 关闭浏览器
        if (driver != null) {
            driver.quit();

        }
    }
}