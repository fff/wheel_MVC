package org.thoughtworks.orc.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Repeatable(Routes.class)
public @interface Route {
    String value() default "/";
}
