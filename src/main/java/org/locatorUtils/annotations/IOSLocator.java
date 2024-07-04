package org.locatorUtils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IOSLocator {

    String id() default "";

    String className() default "";

    String xpath() default "";

    String accessibilityId() default "";

}