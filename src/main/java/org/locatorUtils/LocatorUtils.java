package org.locatorUtils;

import org.openqa.selenium.By;
import java.lang.reflect.Field;
import io.appium.java_client.AppiumBy;
public class LocatorUtils {
    private static final String ENV = System.getenv("ENV") != null ? System.getenv("ENV") : "Android"; // assuming ENV is either "Android" or "IOS"

    public static By getLocator(Object obj, Field field) throws IllegalAccessException {
        if (field.isAnnotationPresent(Locator.class)) {
            Locator locator = field.getAnnotation(Locator.class);
            switch (ENV) {
                case "Android":
                    return getAndroidLocator(locator);
                case "IOS":
                    return getIOSLocator(locator);
                default:
                    throw new RuntimeException("Unsupported environment: " + ENV);
            }
        } else {
            throw new IllegalArgumentException("Field " + field.getName() + " does not have Locator annotation.");
        }
    }

    private static By getAndroidLocator(Locator locator) {
        if (!locator.androidId().isEmpty()) {
            return By.id(locator.androidId());
        } else if (!locator.androidClassName().isEmpty()) {
            return By.className(locator.androidClassName());
        } else if (!locator.androidXpath().isEmpty()) {
            return By.xpath(locator.androidXpath());
        } else if (!locator.androidAccessibilityId().isEmpty()) {
            return AppiumBy.accessibilityId(locator.androidAccessibilityId());
        } else if (!locator.androidUIAutomator().isEmpty()) {
            return AppiumBy.androidUIAutomator(locator.androidUIAutomator());
        } else if (!locator.androidViewTag().isEmpty()) {
            return AppiumBy.androidViewTag(locator.androidViewTag());
        } else {
            throw new RuntimeException("No valid Android locator found.");
        }
    }
    private static By getIOSLocator(Locator locator) {
        if (!locator.iosId().isEmpty()) {
            return By.id(locator.iosId());
        } else if (!locator.iosClassName().isEmpty()) {
            return By.className(locator.iosClassName());
        } else if (!locator.iosXpath().isEmpty()) {
            return By.xpath(locator.iosXpath());
        } else if (!locator.iosAccessibilityId().isEmpty()) {
            return AppiumBy.accessibilityId(locator.iosAccessibilityId());
        } else {
            throw new RuntimeException("No valid iOS locator found.");
        }
    }
    public static void initializeLocators(Object obj) {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Locator.class)) {
                field.setAccessible(true);
                try {
                    By locator = getLocator(obj, field);
                    field.set(obj, locator);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to set locator for field: " + field.getName(), e);
                }
            }
        }
    }
}