package bitwise;
/**
 * Java Program to check if a number is power of two or not.
 *
 * @author Javin
 */
public class PowerOfTwo {

    public static void main(String args[]) {
//        showTest(2);
//        showTest(4);
//        showTest(5);
//        showTest(1);
//        showTest(0);
        for (int i = Integer.MAX_VALUE; i > Integer.MIN_VALUE; i--) {
            if(isPowerOfTwo(i)) {
                showTest(i);
            }
        }
    }

    private static void showTest(int number) {
        System.out.printf("is %d power of Two? %b%n", number, isPowerOfTwo(number));
    }

    /**
     * @param number A positive integer
     * @return true, if number is power of two, otherwise false.
     */
    public static boolean isPowerOfTwo(int number) {
        return (number & (number - 1)) == 0;
    }
}