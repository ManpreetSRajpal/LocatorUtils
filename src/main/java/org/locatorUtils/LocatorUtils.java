package org.locatorUtils;

import org.locatorUtils.annotations.AndroidLocator;
import org.locatorUtils.annotations.IOSLocator;
import org.locatorUtils.strategies.AndroidLocatorStrategy;
import org.locatorUtils.strategies.IOSLocatorStrategy;
import org.locatorUtils.strategies.LocatorStrategy;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

public class LocatorUtils {
    private static final String ENV = System.getenv("ENVIRONMENT") != null ? System.getenv("ENVIRONMENT") : "ANDROID";

    private static LocatorStrategy locatorStrategy;

    static {
        switch (ENV) {
            case "ANDROID":
                locatorStrategy = new AndroidLocatorStrategy();
                break;
            case "IOS":
                locatorStrategy = new IOSLocatorStrategy();
                break;
            default:
                throw new RuntimeException("Unsupported environment: " + ENV);
        }
    }

    public static By getLocator(Object obj, Field field) throws IllegalAccessException {
        if (ENV.equals("ANDROID") && field.isAnnotationPresent(AndroidLocator.class)) {
            AndroidLocator androidLocator = field.getAnnotation(AndroidLocator.class);
            return locatorStrategy.getAndroidLocator(androidLocator);
        } else if (ENV.equals("IOS") && field.isAnnotationPresent(IOSLocator.class)) {
            IOSLocator iosLocator = field.getAnnotation(IOSLocator.class);
            return locatorStrategy.getIOSLocator(iosLocator);
        } else {
            throw new IllegalArgumentException("Field " + field.getName() + " does not have AndroidLocator or IOSLocator annotation.");
        }
    }

    public static void initializeLocators(Object obj) {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(AndroidLocator.class) || field.isAnnotationPresent(IOSLocator.class)) {
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