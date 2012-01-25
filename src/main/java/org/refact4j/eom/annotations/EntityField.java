package org.refact4j.eom.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface EntityField {

    String name();

    String prettyName() default "";

    boolean isKey() default false;

    String defaultValue() default "";

    boolean nullable() default true;

    boolean visible() default true;

    boolean editable() default true;

}
