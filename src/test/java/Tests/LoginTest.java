package Tests;

import Base.Base;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends Base {

    // TC01 - Positivo
    @Test
    public void tc01_loginExitosoConCredencialesValidas() {
        System.out.println("[TC01] Iniciando: login con credenciales válidas");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertEquals(getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        System.out.println("Redirigido correctamente a inventory.html\n");
    }

    // TC02 - Negativo
    @Test
    public void tc02_loginConContrasenaIncorrecta() {
        System.out.println("[TC02] Iniciando: login con contraseña incorrecta");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.open();
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
        System.out.println("Mensaje de error mostrado correctamente\n");
    }

    // TC03 - Negativo
    @Test
    public void tc03_loginConCamposVacios() {
        System.out.println("[TC03] Iniciando: login con campos vacíos");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.open();
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
        System.out.println("Validación de campo requerido funcionando\n");
    }

    // TC04 - Validación de Interfaz
    @Test
    public void tc04_verificarElementosDelFormularioDeLogin() {
        System.out.println("[TC04] Iniciando: verificación de elementos del formulario de login");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.open();

        Assert.assertTrue(loginPage.isLogoDisplayed(),          "Logo no visible");
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Campo Username no visible");
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Campo Password no visible");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(),   "Botón Login no visible");
        System.out.println("Todos los elementos del formulario son visibles\n");
    }
}
