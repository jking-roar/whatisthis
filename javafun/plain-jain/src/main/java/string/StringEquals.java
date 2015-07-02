package string;
public class StringEquals {
    public static void main(String[] args) {
        String myString = "  ";
        System.out.println(myString.trim() == "");
        System.out.println(myString.trim().intern() == "");
        System.out.println(myString.trim().equals(""));
    }
}
