package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By btnAgregarMochila = By.id("add-to-cart-sauce-labs-backpack");
    private By contadorCarrito = By.className("shopping_cart_badge");
    private By iconoCarrito = By.className("shopping_cart_link");
    private By tituloPrimerProducto = By.id("item_4_title_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void agregarProducto() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAgregarMochila)).click();
    }

    public String obtenerNumeroCarrito() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(contadorCarrito)).getText();
    }

    public void abrirDetalleProducto() {
        wait.until(ExpectedConditions.elementToBeClickable(tituloPrimerProducto)).click();
    }

    public void navegarAlCarrito() {
        wait.until(ExpectedConditions.elementToBeClickable(iconoCarrito)).click();
    }
}