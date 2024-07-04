package org.locatorUtils.strategies;

import org.locatorUtils.annotations.AndroidLocator;
import org.locatorUtils.annotations.IOSLocator;
import org.openqa.selenium.By;

public interface LocatorStrategy {
    By getAndroidLocator(AndroidLocator locator);
    By getIOSLocator(IOSLocator locator);
}