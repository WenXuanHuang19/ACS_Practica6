package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartTitle              = By.className("title");
    private final By cartItems              = By.className("cart_item");
    private final By firstItemName          = By.className("inventory_item_name");
    private final By checkoutButton         = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait   = wait;
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle)).getText();
    }

    public List<WebElement> getCartItems() {
        return driver.findElements(cartItems);
    }

    public String getFirstItemName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstItemName)).getText();
    }

    public void removeFirstItem() {
        List<WebElement> removeButtons = driver.findElements(By.cssSelector(".cart_button"));
        if (!removeButtons.isEmpty()) {
            removeButtons.get(0).click();
        }
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShoppingButton).click();
    }

    public boolean isCheckoutButtonDisplayed() {
        return driver.findElement(checkoutButton).isDisplayed();
    }

    public boolean isContinueShoppingButtonDisplayed() {
        return driver.findElement(continueShoppingButton).isDisplayed();
    }
}
