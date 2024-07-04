package org.locatorUtils.strategies;
import io.appium.java_client.AppiumBy;
import org.locatorUtils.annotations.AndroidLocator;
import org.locatorUtils.annotations.IOSLocator;
import org.openqa.selenium.By;
public class AndroidLocatorStrategy implements LocatorStrategy {
    @Override
    public By getAndroidLocator(AndroidLocator locator) {
        if (!locator.id().isEmpty()) {
            return By.id(locator.id());
        } else if (!locator.className().isEmpty()) {
            return By.className(locator.className());
        } else if (!locator.xpath().isEmpty()) {
            return By.xpath(locator.xpath());
        } else if (!locator.accessibilityId().isEmpty()) {
            return AppiumBy.accessibilityId(locator.accessibilityId());
        } else if (!locator.uiAutomator().isEmpty()) {
            return AppiumBy.androidUIAutomator(locator.uiAutomator());
        } else if (!locator.viewTag().isEmpty()) {
            return AppiumBy.androidViewTag(locator.viewTag());
        } else {
            throw new RuntimeException("No valid Android locator found.");
        }
    }

    @Override
    public By getIOSLocator(IOSLocator locator) {
        throw new UnsupportedOperationException("AndroidLocatorStrategy does not support IOSLocator.");
    }
}