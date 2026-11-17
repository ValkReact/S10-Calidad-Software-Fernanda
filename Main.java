package s10calidadsoftwarefernandacambronero;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import javax.swing.JOptionPane;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class Main {


    private static WebDriver iniciarNavegadorLimpio() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        return driver;
    }

    public static void main(String[] args) {
        StringBuilder informe = new StringBuilder("=== REPORTE DE EJECUCIÓN - CALIDAD DEL SOFTWARE ===\n\n");
        WebDriver driver = null;

        try {
            // CP-01
            driver = iniciarNavegadorLimpio();
            LoginPage login1 = new LoginPage(driver);
            login1.autenticar("standard_user", "secret_sauce");
            boolean cp01 = driver.getCurrentUrl().contains("inventory.html");
            informe.append("CP-01 (Inicio de sesión exitoso): ").append(cp01 ? "ÉXITO\n" : "FALLÓ\n");
            driver.quit();

            // CP-02
            driver = iniciarNavegadorLimpio();
            LoginPage login2 = new LoginPage(driver);
            login2.autenticar("standard_user", "clave_invalida");
            boolean cp02 = login2.leerMensajeError().contains("Epic sadface");
            informe.append("CP-02 (Falla por credenciales incorrectas): ").append(cp02 ? "ÉXITO\n" : "FALLÓ\n");
            driver.quit();

            // CP-03
            driver = iniciarNavegadorLimpio();
            new LoginPage(driver).autenticar("standard_user", "secret_sauce");
            ProductsPage prod3 = new ProductsPage(driver);
            prod3.agregarProducto();
            boolean cp03 = prod3.obtenerNumeroCarrito().equals("1");
            informe.append("CP-03 (Agregar producto al carrito): ").append(cp03 ? "ÉXITO\n" : "FALLÓ\n");
            driver.quit();

            // CP-04
            driver = iniciarNavegadorLimpio();
            new LoginPage(driver).autenticar("standard_user", "secret_sauce");
            ProductsPage prod4 = new ProductsPage(driver);
            prod4.abrirDetalleProducto();
            boolean cp04 = driver.getCurrentUrl().contains("inventory-item.html");
            informe.append("CP-04 (Validación de detalle de producto): ").append(cp04 ? "ÉXITO\n" : "FALLÓ\n");
            driver.quit();

            // CP-05
            driver = iniciarNavegadorLimpio();
            new LoginPage(driver).autenticar("standard_user", "secret_sauce");
            ProductsPage prod5 = new ProductsPage(driver);
            prod5.agregarProducto();
            prod5.navegarAlCarrito();
            
            CheckoutPage check5 = new CheckoutPage(driver);
            check5.iniciarCheckout();
            check5.completarDatosEnvio("Fernanda", "Cambronero", "40101");
            check5.confirmarOrden();
            boolean cp05 = check5.obtenerConfirmacion().equals("Thank you for your order!");
            informe.append("CP-05 (Flujo completo de compra): ").append(cp05 ? "ÉXITO\n" : "FALLÓ\n");
            driver.quit();

            // CP-06
            driver = iniciarNavegadorLimpio();
            new LoginPage(driver).autenticar("standard_user", "secret_sauce");
            ProductsPage prod6 = new ProductsPage(driver);
            prod6.agregarProducto();
            prod6.navegarAlCarrito();
            
            CheckoutPage check6 = new CheckoutPage(driver);
            check6.iniciarCheckout();
            check6.continuarSinDatos();
            boolean cp06 = check6.obtenerErrorFormulario().contains("First Name is required");
            informe.append("CP-06 (Validación de campos obligatorios): ").append(cp06 ? "ÉXITO\n" : "FALLÓ\n");
            driver.quit();

        } catch (Exception e) {
            informe.append("\nERROR CRÍTICO: ").append(e.getMessage());
            if (driver != null) {
                driver.quit();
            }
        }

        JOptionPane.showMessageDialog(null, informe.toString(), "Resultados Automatización - Sauce Demo", JOptionPane.INFORMATION_MESSAGE);
    }
}