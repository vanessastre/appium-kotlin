import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.remote.MobilePlatform
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import io.appium.java_client.service.local.flags.GeneralServerFlag

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.remote.DesiredCapabilities

import java.io.File
import java.net.URL
import java.util.concurrent.TimeUnit

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class TestBase {

    lateinit var driver: AndroidDriver<AndroidElement>
    lateinit var service: AppiumDriverLocalService

    @BeforeAll
    fun configureAppium() {
        val path = System.getProperty("user.home")
        val appiumJS = File("$path\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")

        service = AppiumServiceBuilder()
            .withAppiumJS(appiumJS)
            .withIPAddress("0.0.0.0")
            .usingPort(4723)
            .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
            .build()

        service.start()

        val capabilities = DesiredCapabilities()
        capabilities.setCapability("automationName", "UiAutomator2")
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID)
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel5a")
        capabilities.setCapability(MobileCapabilityType.APP, "$path\\OneDrive\\Documentos\\GitHub\\appium-kotlin\\src\\test\\kotlin\\resources\\General-Store.apk")

        driver = AndroidDriver<AndroidElement>(URL("http://0.0.0.0:4723/wd/hub"), capabilities)

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS)
    }

    @AfterAll
    fun tearDown() {
        driver.quit()
        service.stop()
    }
}