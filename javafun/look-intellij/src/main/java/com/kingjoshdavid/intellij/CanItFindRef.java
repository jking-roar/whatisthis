package com.kingjoshdavid.intellij;

import java.lang.reflect.Method;

public class CanItFindRef {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.kingjoshdavid.intellij.WhereIsRef");
        for (Method method : aClass.getDeclaredMethods()) {
            System.out.println(method);
        }
    }
}

@SuppressWarnings("unused")
class WhereIsRef {
    public void itIsInString() {
        System.out.println("okay");
    }
}

