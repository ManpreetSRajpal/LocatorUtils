package org.locatorUtils.strategies;

import io.appium.java_client.AppiumBy;
import org.locatorUtils.annotations.AndroidLocator;
import org.locatorUtils.annotations.IOSLocator;
import org.openqa.selenium.By;

public class IOSLocatorStrategy implements LocatorStrategy {

    @Override
    public By getAndroidLocator(AndroidLocator locator) {
        throw new UnsupportedOperationException("IOSLocatorStrategy does not support AndroidLocator.");
    }

    @Override
    public By getIOSLocator(IOSLocator locator) {
        if (!locator.id().isEmpty()) {
            return By.id(locator.id());
        } else if (!locator.className().isEmpty()) {
            return By.className(locator.className());
        } else if (!locator.xpath().isEmpty()) {
            return By.xpath(locator.xpath());
        } else if (!locator.accessibilityId().isEmpty()) {
            return AppiumBy.accessibilityId(locator.accessibilityId());
        } else {
            throw new RuntimeException("No valid iOS locator found.");
        }
    }
}