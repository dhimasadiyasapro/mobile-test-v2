package base;

import constants.PropertyConfigs;
import helpers.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverSetup {

    private static final String APPIUM_URL = ConfigReader.getInstance().getValue(PropertyConfigs.APPIUM_URL);
    private static final String PLATFORM_NAME = ConfigReader.getInstance().getValue(PropertyConfigs.PLATFORM_NAME);
    private static final String PLATFORM_VERSION = ConfigReader.getInstance().getValue(PropertyConfigs.PLATFORM_VERSION);
    private static final String DEVICE_NAME = ConfigReader.getInstance().getValue(PropertyConfigs.DEVICE_NAME);
    private static final String APP_PATH = System.getProperty("user.dir") + ConfigReader.getInstance().getValue(PropertyConfigs.APP_PATH);

    public static WebDriver setupDriver(String driverType) throws MalformedURLException {
        WebDriver driver;
        switch (driverType) {
            case "iOS":
                driver = new IOSDriver<>(new URL(APPIUM_URL), getIOSCapabilities());
                break;
            case "Android":
                driver = new AndroidDriver<>(new URL(APPIUM_URL), getAndroidCapabilities());
                break;
            default:
                driver = null;
        }
        return driver;
    }

    public static DesiredCapabilities getIOSCapabilities() {
        String DEVICE_UDID = ConfigReader.getInstance().getValue(PropertyConfigs.DEVICE_UDID);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        caps.setCapability(MobileCapabilityType.UDID, DEVICE_UDID);
        caps.setCapability(MobileCapabilityType.APP, APP_PATH);
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        return caps;
    }

    public static DesiredCapabilities getAndroidCapabilities() {
        String APP_ACTIVITY = ConfigReader.getInstance().getValue(PropertyConfigs.APP_ACTIVITY);
        String APP_PACKAGE = ConfigReader.getInstance().getValue(PropertyConfigs.APP_PACKAGE);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        caps.setCapability("appActivity", APP_ACTIVITY);
        caps.setCapability("appPackage", APP_PACKAGE);
        caps.setCapability(MobileCapabilityType.APP, APP_PATH);
        return caps;
    }
}
