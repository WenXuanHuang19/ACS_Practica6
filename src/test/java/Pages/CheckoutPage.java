package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameField     = By.id("first-name");
    private final By lastNameField      = By.id("last-name");
    private final By postalCodeField    = By.id("postal-code");
    private final By continueButton     = By.id("continue");
    private final By finishButton       = By.id("finish");
    private final By errorMessage       = By.cssSelector("[data-test='error']");
    private final By confirmationHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait   = wait;
    }

    public void fillForm(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationHeader)).getText();
    }

    public boolean isFirstNameFieldDisplayed() {
        return driver.findElement(firstNameField).isDisplayed();
    }

    public boolean isLastNameFieldDisplayed() {
        return driver.findElement(lastNameField).isDisplayed();
    }

    public boolean isPostalCodeFieldDisplayed() {
        return driver.findElement(postalCodeField).isDisplayed();
    }

    public boolean isContinueButtonDisplayed() {
        return driver.findElement(continueButton).isDisplayed();
    }
}
