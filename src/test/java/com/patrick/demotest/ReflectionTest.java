package com.patrick.demotest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.*;
import java.util.Locale;

@SpringBootTest
public class ReflectionTest {

    @Test
    void getMethodsOfClassFromStringTest() {
        System.out.println("Test to get the methods of a class from a string");
        System.out.println("================================================");
        String stringClass1 = "com.patrick.demotest.Person";
        try {
            Class class1 = Class.forName(stringClass1);
            Method methodClass1[] = class1.getDeclaredMethods();
            int count=0;
            for (Method method:methodClass1) {
                count++;
                System.out.println("Method"+ count +" : "+method.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getMethodsOfClassFromObjectTest() {
        System.out.println("Test to get the methods of a class from an object");
        System.out.println("=================================================");
        Person objectClass1 = new Person();
        Class class1 = objectClass1.getClass();
        Method methodClass1[] = class1.getDeclaredMethods();
        int count = 0;
        for (Method method : methodClass1) {
            count++;
            System.out.println("Method" + count + " : " + method.getName());
        }
    }

    @Test
    void getConstructorsOfClassTest() {
        System.out.println("Test to get the constructors of a class and the parameters associated");
        System.out.println("=====================================================================");
        Person objectClass1 = new Person();
        Class class1 = objectClass1.getClass();
        Constructor constructorsClass1[] = class1.getConstructors();
        int count = 0;
        for (Constructor constructor : constructorsClass1) {
            count++;
            System.out.println("Constructor" + count + " : " + constructor.getName());
            System.out.println("Nb of parameters : " + constructor.getParameterCount());
            Parameter[] parameters = constructor.getParameters();
            int count2=0;
            for (Parameter param : parameters) {
                System.out.println(">>> Parameter"+count2+" : "+param.getName());
                count2++;
            }
        }
    }

    @Test
    void getInstantiateObjectTest() {
        System.out.println("Instantiate an object and set values to fields");
        System.out.println("==============================================");
        String stringClass1 = "com.patrick.demotest.Person";
        Class class1 = null;
        try {
            class1 = Class.forName(stringClass1);
            Constructor constructorsClass1[] = class1.getConstructors();
            int count = 0;
            for (Constructor constructor : constructorsClass1) {
                if (constructor.getParameters().length == 0) {
                    Object object1 = constructor.newInstance();
                    if (object1 instanceof Person) {
                        System.out.println("Object created instance of Person");
                    }
                    Method[] object1Methods = object1.getClass().getDeclaredMethods();
                    int count2=0;
                    for (Method method : object1Methods) {
                        count2++;
                        System.out.println("Method"+count2+" : "+method.getName());
                    }
                    Field[] fields = object1.getClass().getDeclaredFields();
                    int count3=0;
                    for (Field field : fields) {
                        String firstLetterFieldName = String.valueOf(field.getName().subSequence(0,1)).toUpperCase(Locale.ROOT);
                        String restFieldName = String.valueOf(field.getName().subSequence(1,field.getName().length()));
                        String fieldNameForMethod = firstLetterFieldName+restFieldName;
                        Method setField = object1.getClass().getMethod("set"+fieldNameForMethod, field.getType());
                        if (field.getType().isAssignableFrom(int.class)) {
                            setField.invoke(object1,count3);
                        } else {
                            setField.invoke(object1,"string for "+field.getName());
                        }
                        System.out.println(field.getName()+" : "+field.get(object1));
                        count3++;
                    }
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
