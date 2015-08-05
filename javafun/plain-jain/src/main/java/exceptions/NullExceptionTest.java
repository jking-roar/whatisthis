package exceptions;

public class NullExceptionTest {
    // Attempting to throw a null exception will throw a NullPointerException
    public static void main(String[] args) {
        new NullExceptionTest().test();
    }

    private void test() {
        try {
            final IllegalStateException e = null;
            throwException(e);
        } catch(NullPointerException npe) {
            System.out.println("null");
        } catch (IllegalStateException r) {
            System.out.println("illegal");
        }
        try {
            final IllegalStateException e = new IllegalStateException();
            throwException(e);
        } catch(NullPointerException npe) {
            System.out.println("null");
        } catch (IllegalStateException r) {
            System.out.println("illegal");
        }
        try {
            throw null;
        } catch (NullPointerException e) {
            System.out.println("caught it");
        }
    }

    private static <E extends Exception> void throwException(E e)  throws E {
        throw e;
    }
}
