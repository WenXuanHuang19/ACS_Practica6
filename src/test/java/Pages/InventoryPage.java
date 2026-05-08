package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle        = By.className("title");
    private final By inventoryItems   = By.className("inventory_item");
    private final By cartIcon         = By.className("shopping_cart_link");
    private final By cartBadge        = By.className("shopping_cart_badge");
    private final By addToCartButtons = By.cssSelector(".btn_inventory");
    private final By productNames     = By.className("inventory_item_name");

    public InventoryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait   = wait;
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public boolean isTitleDisplayed() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public List<WebElement> getInventoryItems() {
        return driver.findElements(inventoryItems);
    }

    public void addFirstProductToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public String getFirstProductName() {
        List<WebElement> names = driver.findElements(productNames);
        return names.isEmpty() ? "" : names.get(0).getText();
    }

    public void clickFirstProduct() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames));
        driver.findElements(productNames).get(0).click();
    }

    public void clickCartIcon() {
        driver.findElement(cartIcon).click();
    }

    public boolean isCartIconDisplayed() {
        return driver.findElement(cartIcon).isDisplayed();
    }

    public String getCartBadgeCount() {
        List<WebElement> badges = driver.findElements(cartBadge);
        return badges.isEmpty() ? "0" : badges.get(0).getText();
    }
}
