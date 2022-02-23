package com.patrick.demotest.inheritance;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationForClass {
    String version();
}
