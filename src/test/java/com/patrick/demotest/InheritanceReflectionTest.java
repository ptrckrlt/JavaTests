package com.patrick.demotest;

import com.patrick.demotest.inheritance.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InheritanceReflectionTest {

    @Test
    void reflectionTest() {
        InitialClass initialClass1 = new InitialClass1("A","B");
        checkClass(initialClass1);
        InitialClass initialClass2 = new InitialClass2("C","D","E");
        checkClass(initialClass2);
    }

    void checkClass(InitialClass initialClass) {
        System.out.println("Object "+initialClass.getClass().getName());
        Method[] methods = initialClass.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method = " + method.getName());
        }
        Field field = null;
        try {
            field = initialClass.getClass().getDeclaredField("string1");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println("Annotation for string1 = " + field.getAnnotation(AnnotationForClass.class).version());
    }

}
