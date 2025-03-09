import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AngularEclinicAppTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Set the path of  EdgeDriver
        System.setProperty("webdriver.edge.driver", "C:\\chengcoding\\Java\\edgedriver_win32\\msedgedriver.exe");
        // Create EdgeDriver instance
        driver = new EdgeDriver();
    }


    @Test
    public void testNavigation(){
        driver.manage().window().maximize();

        //  websites
        driver.get("http://localhost:4200");

        String pageTitle = driver.getTitle();

        assertTrue(pageTitle.contains("Eclinic"));

        WebElement loginButton = driver.findElement(By.xpath("//div[@class='extended-button-container']//button[@id='login']"));
        loginButton.click();

        driver.navigate().back();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("dashboard"));


    }

    @Test
    public void testSearch(){
        String expectedAlert = "You are visiting knowledge base.";

        driver.manage().window().maximize();

        //  websites
        driver.get("http://localhost:4200");

        WebElement searchButton = driver.findElement(By.xpath("//div[@class='extended-button-container']//button[@aria-label='search']"));
        searchButton.click();

        //Wait for elements to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement submitButton = driver.findElement(By.xpath("//div[@class='overlay']//button[@id='search']"));
        submitButton.click();


        // catch alert
        Alert alert = driver.switchTo().alert();
        String actualAlert = alert.getText();
        assertTrue(actualAlert.contains(expectedAlert));
        alert.accept();

    }

    @Test
    public void testLogin() {
        driver.manage().window().maximize();

        //  websites
        driver.get("http://localhost:4200");

        String pageTitle = driver.getTitle();


        assertTrue(pageTitle.contains("Eclinic"));

        WebElement loginButton = driver.findElement(By.xpath("//div[@class='extended-button-container']//button[@id='login']"));
        loginButton.click();

        //Wait for elements to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // location and interaction
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("tom1@gmail.com");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("12345678");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        //Wait for elements to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // xpath
        WebElement logoutButton = driver.findElement(By.xpath("//div[@class='extended-button-container']//button[@id='logout']"));

        String logoutText = logoutButton.getText();
        String expectedText="Tom" + "  Logout";
        assertTrue(logoutText.contains(expectedText));

    }

    @AfterEach
    public void tearDown() {
        // 关闭浏览器
        if (driver != null) {
            driver.quit();

        }
    }
}
