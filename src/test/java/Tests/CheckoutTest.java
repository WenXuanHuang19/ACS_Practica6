package Tests;

import Base.Base;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends Base {

    private void loginAddProductAndGoToCheckout() {
        navigateTo("https://www.saucedemo.com/");
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver, wait);
        inventoryPage.addFirstProductToCart();
        inventoryPage.clickCartIcon();
        new CartPage(driver, wait).clickCheckout();
    }

    // TC13 - Navegación
    @Test
    public void tc13_navegarAlCheckoutDesdeElCarrito() {
        System.out.println("[TC13] Iniciando: navegación al checkout desde el carrito");
        navigateTo("https://www.saucedemo.com/");
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver, wait);
        inventoryPage.addFirstProductToCart();
        inventoryPage.clickCartIcon();
        new CartPage(driver, wait).clickCheckout();

        Assert.assertEquals(getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        System.out.println("Redirigido correctamente a checkout-step-one.html\n");
    }

    // TC14 - Positivo
    @Test
    public void tc14_completarCheckoutConDatosValidos() {
        System.out.println("[TC14] Iniciando: completar checkout con datos válidos");
        loginAddProductAndGoToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver, wait);
        checkoutPage.fillForm("Juan", "Perez", "22000");
        checkoutPage.clickContinue();

        Assert.assertEquals(getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        System.out.println("Formulario aceptado, en resumen de orden (step 2)");

        checkoutPage.clickFinish();
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!");
        System.out.println("Orden completada: 'Thank you for your order!'\n");
    }

    // TC15 - Negativo
    @Test
    public void tc15_checkoutConCamposVaciosMuestraError() {
        System.out.println("[TC15] Iniciando: checkout con campos vacíos");
        loginAddProductAndGoToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver, wait);
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"));
        System.out.println("Error de validación mostrado: 'First Name is required'\n");
    }

    // TC16 - Validación de Interfaz
    @Test
    public void tc16_verificarElementosDelFormularioDeCheckout() {
        System.out.println("[TC16] Iniciando: verificación de elementos del formulario de checkout");
        loginAddProductAndGoToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver, wait);

        Assert.assertTrue(checkoutPage.isFirstNameFieldDisplayed(),  "Campo First Name no visible");
        Assert.assertTrue(checkoutPage.isLastNameFieldDisplayed(),   "Campo Last Name no visible");
        Assert.assertTrue(checkoutPage.isPostalCodeFieldDisplayed(), "Campo Postal Code no visible");
        Assert.assertTrue(checkoutPage.isContinueButtonDisplayed(),  "Botón Continue no visible");
        System.out.println("Todos los campos y el botón Continue son visibles\n");
    }
}
