import org.junit.jupiter.api.*

import org.junit.jupiter.api.Test
import org.openqa.selenium.WebElement
import HomePage
import BasePage
import io.appium.java_client.android.AndroidDriver

class Test1 : TestBase() {

    @Test
    fun testOne() {
        val homePage = HomePage(driver)

        homePage.enterName("Felipe")
        homePage.selectCountry("Colombia")
    }
}