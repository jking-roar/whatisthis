package boxing;

public class IntegerEquality {
    public static void main(String[] args) {

        reportEqualsEquals(1, 1);
        reportEqualsEquals(1000, 1000);
    }

    private static void reportEqualsEquals(Integer a, Integer b) {
        System.out.println("" + a + " = " + b + "? " + (a == b));
    }
}
