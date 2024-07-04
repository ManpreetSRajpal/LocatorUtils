package org.locatorUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Locator {

    String androidId() default "";

    String androidClassName() default "";

    String androidXpath() default "";

    String androidAccessibilityId() default "";

    String androidUIAutomator() default "";

    String androidViewTag() default "";

    String iosId() default "";

    String iosClassName() default "";

    String iosXpath() default "";

    String iosAccessibilityId() default "";

    String iosUIAutomation() default "";
}