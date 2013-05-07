package org.thoughtworks.orc.internal.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Repeatable(Routes.class)
public @interface Route {
    String value() default "/";
}
