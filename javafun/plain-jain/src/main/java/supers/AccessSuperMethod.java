package supers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AccessSuperMethod {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final SubClazz subClazz = new SubClazz();
        subClazz.method();

        final Method method = Clazz.class.getDeclaredMethod("method");
        method.invoke(subClazz);

        subClazz.callMethodInSuper();
    }
}

class Clazz {
    @SuppressWarnings ("unused")
    public void method() {
        System.out.println("in Clazz");
    }
}

class SubClazz extends Clazz{
    @Override
    public void method() {
        System.out.println("in SubClazz");
    }

    public void callMethodInSuper() {
        super.method();
    }
}
