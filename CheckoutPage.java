package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By btnCheckout = By.id("checkout");
    private By inputNombre = By.id("first-name");
    private By inputApellido = By.id("last-name");
    private By inputCodigoPostal = By.id("postal-code");
    private By btnContinuar = By.id("continue");
    private By btnFinalizar = By.id("finish");
    private By tituloConfirmacion = By.className("complete-header");
    private By errorFormulario = By.cssSelector("[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void iniciarCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(btnCheckout)).click();
    }

    public void completarDatosEnvio(String nombre, String apellido, String postal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputNombre)).sendKeys(nombre);
        driver.findElement(inputApellido).sendKeys(apellido);
        driver.findElement(inputCodigoPostal).sendKeys(postal);
        driver.findElement(btnContinuar).click();
    }

    public void confirmarOrden() {
        wait.until(ExpectedConditions.elementToBeClickable(btnFinalizar)).click();
    }

    public String obtenerConfirmacion() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(tituloConfirmacion)).getText();
    }

    public void continuarSinDatos() {
        wait.until(ExpectedConditions.elementToBeClickable(btnContinuar)).click();
    }

    public String obtenerErrorFormulario() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorFormulario)).getText();
    }
}