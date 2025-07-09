import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Navigation {

    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        WebDriverManager.edgedriver().setup(); // Use Edge WebDriver

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");

        driver = new EdgeDriver(options); // Launch Edge with options
        driver.manage().window().maximize();
    }

    @AfterTest
    public void afterTest() throws InterruptedException {
        Thread.sleep(9000);
        driver.quit();
    }

    @Test(priority = 0)
    public void homepage() {
        System.out.println("Opening up the browser");
        driver.get("https://katalon-demo-cura.herokuapp.com/");
    }

    @Test(priority = 1)
    public void makeAppointment() {
        driver.findElement(By.id("btn-make-appointment")).click();
    }

    @Test(priority = 2)
    public void login() {
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();
    }

    @Test(priority = 3)
    public void bookAppointment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("combo_facility")));

        Random random = new Random();

        WebElement facilityDropdown = driver.findElement(By.id("combo_facility"));
        Select facilitySelect = new Select(facilityDropdown);
        List<WebElement> options = facilitySelect.getOptions();
        int randomIndex = random.nextInt(options.size());
        facilitySelect.selectByIndex(randomIndex);

        WebElement readmissionCheckbox = driver.findElement(By.id("chk_hospotal_readmission"));
        if (random.nextBoolean()) {
            readmissionCheckbox.click();
        }

        List<String> programs = Arrays.asList("radio_program_medicare", "radio_program_medicaid", "radio_program_none");
        String selectedProgram = programs.get(random.nextInt(programs.size()));
        driver.findElement(By.id(selectedProgram)).click();

        LocalDate futureDate = LocalDate.now().plusDays(random.nextInt(60) + 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        driver.findElement(By.id("txt_visit_date")).sendKeys(futureDate.format(formatter));

        String[] comments = {
                "Automated test appointment",
                "Checking Selenium form input",
                "Booking via random generator",
                "This is a test run",
                "Just verifying functionality"
        };
        driver.findElement(By.id("txt_comment")).sendKeys(comments[random.nextInt(comments.length)]);

        driver.findElement(By.id("btn-book-appointment")).click();
    }

    @Test(priority = 4)
    public void verifyConfirmation() {
        String confirmation = driver.findElement(By.tagName("h2")).getText();
        Assert.assertEquals(confirmation, "Appointment Confirmation", "Appointment not confirmed!");
        System.out.println("Appointment booked successfully.");
    }

    @Test(priority = 5)
    public void logout() {
        driver.findElement(By.id("menu-toggle")).click();
        driver.findElement(By.linkText("Logout")).click();
    }
}




