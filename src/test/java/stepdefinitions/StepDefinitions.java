package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StepDefinitions {
    WebDriver driver;

    @Given("the user is on the Saucedemo login page")
    public void navigateToLoginPage() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @When("enter credentials, username {string} and password {string}")
    public void enterCredentials(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }

    @Then("clicks the 'Login' button")
    public void clickTheLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("the user is redirected to the product page")
    public void redirectToProductPage(){
        WebElement productLabel = driver.findElement(By.className("app_logo"));
        assert productLabel.isDisplayed();
        driver.quit();
    }

    @Then("the user receives an error message and remains on the login page")
    public void receiveErrorMessageWhenLoginFailed(){
        WebElement errorMessage = driver.findElement(By.cssSelector(".error-message-container.error"));
        assertTrue(errorMessage.isDisplayed());
        assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
        driver.quit();
    }

    /* Checkout */
    @And("the user selects a product to purchase")
    public void selectProduct() {
        WebElement product = driver.findElement(By.cssSelector(".inventory_item:nth-child(1)"));
        product.click();
    }

    @When("adds it to the cart")
    public void addToCart() {
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();
    }

    @When("clicks the 'Checkout' button")
    public void clickCheckoutButton() {
        WebElement cartButton = driver.findElement(By.id("shopping_cart_container"));
        cartButton.click();

        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();
    }

    @And("fill first name, last name, and zip code")
    public void fillPersonalInfo() {
        WebElement firstNameField = driver.findElement(By.id("first-name"));
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        WebElement zipCodeField = driver.findElement(By.id("postal-code"));

        firstNameField.sendKeys("John");
        lastNameField.sendKeys("Doe");
        zipCodeField.sendKeys("12345");
    }

    @And("clicks the 'continue' button")
    public void clickContinueButton() {
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
    }

    @And("clicks the 'finish' button")
    public void clickFinishButton() {
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();
    }

    @Then("the user successfully proceeds to the checkout overview page")
    public void verifyCheckoutOverviewPage() {
        WebElement checkoutOverview = driver.findElement(By.id("checkout_complete_container"));
        assertTrue(checkoutOverview.isDisplayed());
        driver.quit();
    }

    @Then("the user receives an error message indicating the cart is empty")
    public void verifyEmptyCartErrorMessage() {
        try {
            driver.findElement(By.cssSelector(".error-message-container.error"));
        } catch (Exception e) {
            throw new AssertionError("Error message element not found. Test step failed.", e);
        } finally {
            driver.quit();
        }
    }

    /* Logout */
    @And("go to product page")
    public void goToProductPage() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    @When("clicks the 'logout' button")
    public void clickLogoutButton() {
        WebElement logoutWrapper = driver.findElement(By.id("react-burger-menu-btn"));
        logoutWrapper.click();
        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();
    }

    @Then("verify the user is on login page")
    public void verifyLoginPage() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        assertTrue(loginButton.isDisplayed());
        driver.quit();
    }

    @Then("verify user cannot go to the product page")
    public void verifyCannotGoToProductPage() {
        String currentUrl = driver.getCurrentUrl();
        assertFalse("User is redirected to the product page after logout",
                currentUrl.contains("inventory.html"));

        driver.quit();
    }

    /* Product */
    @When("the user filter product")
    public void userSelectsSortingOption() {
        Select sortingDropdown = new Select(driver.findElement(By.className("product_sort_container")));
        sortingDropdown.selectByValue("za");
    }
}