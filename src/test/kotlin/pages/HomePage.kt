import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.MobileBy
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

class HomePage(driver: AndroidDriver<AndroidElement>) : BasePage(driver) {

    // Elements
    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private lateinit var nameField: AndroidElement

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private lateinit var countryDropDown: AndroidElement

    // Methods

    fun enterName(name: String) {
       typeElement(nameField, name)
    }

    fun selectCountry(countryName: String) {
        tapElement(countryDropDown)
        val country = driver.findElement(
            MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"$countryName\"))"
            )
        )
        country.click()
    }
}