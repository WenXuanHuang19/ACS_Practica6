package Tests;

import Base.Base;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventoryTest extends Base {

    private void loginAsStandardUser() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
    }

    // TC05 - Positivo
    @Test
    public void tc05_agregarPrimerProductoAlCarrito() {
        System.out.println("[TC05] Iniciando: agregar primer producto al carrito");
        loginAsStandardUser();
        InventoryPage inventoryPage = new InventoryPage(driver, wait);
        inventoryPage.addFirstProductToCart();

        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1");
        System.out.println("Producto agregado, badge del carrito muestra 1\n");
    }

    // TC06 - Validación de Interfaz
    @Test
    public void tc06_verificarTituloYElementosPaginaInventario() {
        System.out.println("[TC06] Iniciando: verificación de título y elementos del inventario");
        loginAsStandardUser();
        InventoryPage inventoryPage = new InventoryPage(driver, wait);

        Assert.assertTrue(inventoryPage.isTitleDisplayed(), "Título no visible");
        Assert.assertEquals(inventoryPage.getTitle(), "Products");
        Assert.assertFalse(inventoryPage.getInventoryItems().isEmpty(), "No se muestran productos");
        System.out.println("Título 'Products' visible y productos listados correctamente\n");
    }

    // TC07 - Navegación
    @Test
    public void tc07_navegarAlDetalleDeUnProducto() {
        System.out.println("[TC07] Iniciando: navegación al detalle de un producto");
        loginAsStandardUser();
        InventoryPage inventoryPage = new InventoryPage(driver, wait);
        inventoryPage.clickFirstProduct();

        Assert.assertTrue(getCurrentUrl().contains("inventory-item.html"));
        System.out.println("Redirigido correctamente a la página de detalle del producto\n");
    }

    // TC08 - Navegación
    @Test
    public void tc08_navegarAlCarritoDesdeBarraDeNavegacion() {
        System.out.println("[TC08] Iniciando: navegación al carrito desde la barra de navegación");
        loginAsStandardUser();
        InventoryPage inventoryPage = new InventoryPage(driver, wait);

        Assert.assertTrue(inventoryPage.isCartIconDisplayed(), "Ícono del carrito no visible");
        inventoryPage.clickCartIcon();

        Assert.assertEquals(getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        System.out.println("Redirigido correctamente a cart.html\n");
    }
}
