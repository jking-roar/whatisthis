package regex;

public class StartOfWordReplacement {
    public static void main(String[] args) {
        removeIsOrGetFromGetter("isSomething");

        removeIsOrGetFromGetter("getSomething");

        removeIsOrGetFromGetter("doSomething");


        String interesting = "getsomegetter";
        final String bad = interesting.replace("get", "");
        System.out.println(bad);

        String toTest = "getOutOfHere";
        System.out.println(toTest.matches("^(?:is|get).*"));
    }

    private static void removeIsOrGetFromGetter(String s) {
        final String outcome = s.replaceFirst("^(?:is|get)", "");
        System.out.println(outcome);
    }
}
