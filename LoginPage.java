package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By inputUsuario = By.id("user-name");
    private By inputClave = By.id("password");
    private By botonLogin = By.id("login-button");
    private By alertaError = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void autenticar(String usuario, String clave) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUsuario)).sendKeys(usuario);
        driver.findElement(inputClave).sendKeys(clave);
        driver.findElement(botonLogin).click();
    }

    public String leerMensajeError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alertaError)).getText();
    }
}