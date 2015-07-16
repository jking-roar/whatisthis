import java.util.ArrayList;

public class OnlyCompilableInJava6 {
    public static void main(String[] args) {
        try {
            new OnlyCompilableInJava6().willNotCompileInJava8();
        } catch (Foo foo) {
            System.out.println(foo + " happened");
        }
    }

//    private void willNotCompileInJava6() {
//        ArrayList<String> strings = new ArrayList<>();
//
//        strings.add("java 8");
//        strings.add("allows");
//        strings.add("diamond");
//        System.out.println(strings);
//    }

    public void willNotCompileInJava8() throws Foo {
        try {
            throw new DaughterOfFoo();
        } catch (final Foo exception) {
            try {
                throw exception; // used to throw Foo, now throws DaughterOfFoo
            } catch (SonOfFoo anotherException) { // Reachable?	}
            }
        }
    }

    class Foo extends Exception {
    }

    class SonOfFoo extends Foo {
    }

    class DaughterOfFoo extends Foo {
    }

}