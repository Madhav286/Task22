package dropdownsync;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class FormAutomation {

    public static void main(String[] args) {
        // Set the path to your WebDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize the ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the form page
            driver.get("https://phptravels.com/demo/");

            // Create a WebDriverWait instance with a timeout of 10 seconds
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Fill in the form details
            WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("first_name")));
            firstName.sendKeys("John");

            WebElement lastName = driver.findElement(By.name("last_name"));
            lastName.sendKeys("Doe");

            WebElement businessName = driver.findElement(By.name("business_name"));
            businessName.sendKeys("Example Business");

            WebElement email = driver.findElement(By.name("email"));
            email.sendKeys("john.doe@example.com");

            // Add the logic for sum verification (assuming there is a CAPTCHA or similar element)
            WebElement captchaResult = driver.findElement(By.id("number"));
            int sum = Integer.parseInt(captchaResult.getAttribute("value"));
            WebElement captchaInput = driver.findElement(By.id("captcha"));
            captchaInput.sendKeys(String.valueOf(sum));

            // Submit the form
            WebElement submitButton = driver.findElement(By.id("demo"));
            submitButton.click();

            // Verify that the form is submitted successfully by checking the message displayed
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
            if (successMessage.getText().contains("Thank you")) {
                System.out.println("Form submitted successfully!");
            } else {
                System.out.println("Form submission failed.");
            }

            // Take a screenshot of the page after the form submission
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("form_submission_screenshot.png"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}


