package varargs;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

class NullForVarArg {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        final NullForVarArg thing = new NullForVarArg();
        thing.callMethod();
        thing.callMethod((String)null);
        thing.callMethod("hello", "roger");

        final Method callMethodWithOneArg = NullForVarArg.class.getDeclaredMethod("callMethodWithOneArg", String.class);

        callMethodWithOneArg.invoke(thing, (String)null);
    }

    void callMethod(String ... words) {
        System.out.println(Arrays.toString(words));
    }

    @SuppressWarnings ("unused")
    void callMethodWithOneArg(String arg) {
        System.out.println("called with arg: " + arg);
    }
}