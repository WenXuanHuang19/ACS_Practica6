package Tests;

import Base.Base;
import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends Base {

    private void loginAddProductAndGoToCart() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver, wait);
        inventoryPage.addFirstProductToCart();
        inventoryPage.clickCartIcon();
    }

    // TC09 - Positivo
    @Test
    public void verificarQueProductoApareceEnElCarrito() {
        System.out.println("[TC09] Iniciando: verificar que el producto aparece en el carrito");
        navigateTo("https://www.saucedemo.com/");
        loginAddProductAndGoToCart();
        CartPage cartPage = new CartPage(driver, wait);

        Assert.assertEquals(cartPage.getCartItems().size(), 1);
        Assert.assertFalse(cartPage.getFirstItemName().isEmpty(), "El nombre del producto está vacío");
        System.out.println("Producto encontrado en el carrito: " + cartPage.getFirstItemName()+"\n");
    }

    // TC10 - Negativo
    @Test
    public void eliminarProductoDelCarritoYVerificarQueDesaparece() {
        System.out.println("[TC10] Iniciando: eliminar producto del carrito y verificar que desaparece");
        navigateTo("https://www.saucedemo.com/");
        loginAddProductAndGoToCart();
        CartPage cartPage = new CartPage(driver, wait);
        cartPage.removeFirstItem();

        Assert.assertEquals(cartPage.getCartItems().size(), 0);
        System.out.println("Carrito vacío después de eliminar el producto\n");
    }

    // TC11 - Validación de Interfaz
    @Test
    public void verificarElementosVisiblesEnPaginaCarrito() {
        System.out.println("[TC11] Iniciando: verificación de elementos visibles en la página del carrito");
        navigateTo("https://www.saucedemo.com/");
        loginAddProductAndGoToCart();
        CartPage cartPage = new CartPage(driver, wait);

        Assert.assertEquals(cartPage.getTitle(), "Your Cart");
        Assert.assertTrue(cartPage.isCheckoutButtonDisplayed(),         "Botón Checkout no visible");
        Assert.assertTrue(cartPage.isContinueShoppingButtonDisplayed(), "Botón Continue Shopping no visible");
        System.out.println("Título, botón Checkout y botón Continue Shopping son visibles\n");
    }

    // TC12 - Navegación
    @Test
    public void regresarAlInventarioDesdeElCarrito() {
        System.out.println("[TC12] Iniciando: regresar al inventario desde el carrito");
        navigateTo("https://www.saucedemo.com/");
        loginAddProductAndGoToCart();
        new CartPage(driver, wait).clickContinueShopping();

        Assert.assertEquals(getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        System.out.println("Redirigido correctamente a inventory.html\n");
    }
}
