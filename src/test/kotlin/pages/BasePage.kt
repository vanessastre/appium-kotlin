import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.junit.jupiter.api.Assertions.assertTrue
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import java.time.Duration

open class BasePage(protected val driver: AndroidDriver<AndroidElement>) {

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this)
    }

    fun getElement(element: WebElement) {
        assertTrue(element.isDisplayed)
    }

    fun goBack() {
        driver.navigate().back()
    }

    fun tapElement(element: WebElement) {
        getElement(element)
        element.click()
    }

    fun typeElement(element: WebElement, text: String) {
        getElement(element)
        element.clear()
        element.sendKeys(text)
    }

    fun assertElementDisplayed(element: WebElement) {
        getElement(element)
    }
}