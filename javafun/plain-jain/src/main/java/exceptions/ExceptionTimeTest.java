package exceptions;

/**
 * Adapted from http://stackoverflow.com/questions/299068/how-slow-are-java-exceptions#answer-299315
 */
public class ExceptionTimeTest {
    private static final int ITERATIONS = 10000000;
    private int value;

    public static void main(String[] args) {
        int i;
        ExceptionTimeTest t = new ExceptionTimeTest();

        long originalTime = System.currentTimeMillis();
        t.reset();
        for (i = 1; i < ITERATIONS; i++) {
            t.calculation(i);
        }
        originalTime = System.currentTimeMillis() - originalTime;
        System.out.println("calculation took " + originalTime + " ms, result was " + t.getValue());

        long tryButNoCatch = System.currentTimeMillis();
        t.reset();
        for (i = 1; i < ITERATIONS; i++) {
            try {
                t.declaresButNeverThrows(i);
            } catch (Exception e) {
                System.out.println("You'll never see this!");
            }
        }
        tryButNoCatch = System.currentTimeMillis() - tryButNoCatch;
        System.out.println("tryButNoCatch took " + tryButNoCatch + " ms, result was " + t.getValue());
        System.out.println(String.format("percentage of original: \u00D7 %,.2f%%", tryButNoCatch * 1.0 / originalTime));

        long catchingExceptions = System.currentTimeMillis();
        t.reset();
        for (i = 1; i < ITERATIONS; i++) {
            try {
                t.throwsManyTimes(i);
            } catch (Exception e) {
                // Do nothing here, as we will get here
            }
        }
        catchingExceptions = System.currentTimeMillis() - catchingExceptions;
        System.out.println("throwsManyTimes took " + catchingExceptions + " ms, result was " + t.getValue());
        System.out.println(String.format("Compared to original: \u00D7 %,.2f", catchingExceptions * 1.0 / originalTime));
    }

    public int getValue() {
        return value;
    }

    public void reset() {
        value = 0;
    }

    private void doCalculation(int i) {
        value = ((value + i) / i) << 1;
    }

    /** Calculates without exception */
    public void calculation(int i) {
        doCalculation(i);
        // Will never be true
        //noinspection IncompatibleBitwiseMaskOperation
        if ((i & 0xFFFFFFF) == 1000000000) {
            System.out.println("You'll never see this!");
        }
    }

    /** Could in theory throw one, but never will */
    public void declaresButNeverThrows(int i) throws Exception {
        doCalculation(i);
        // Will never be true
        //noinspection IncompatibleBitwiseMaskOperation
        if ((i & 0xFFFFFFF) == 1000000000) {
            throw new Exception();
        }
    }

    /** This one will regularly throw one */
    public void throwsManyTimes(int i) throws Exception {
        doCalculation(i);
        // i & 1 is equally fast to calculate as i & 0xFFFFFFF; it is both
        // an AND operation between two integers. The size of the number plays
        // no role. AND on 32 BIT always ANDs all 32 bits
        if ((i & 0x1) == 1) {
            throw new Exception();
        }
    }
}