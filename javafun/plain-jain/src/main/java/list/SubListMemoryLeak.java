package list;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SubListMemoryLeak {
    public static void main(String[] args) {
        Fun<List<String>, List<String>> transmogrifierWithLeaks = SubListMemoryLeak::leakySubList;
        Fun<List<String>, List<String>> transmogrifierWithoutLeaks = SubListMemoryLeak::fixedLeakSubList;

        System.out.println("not leaking memory");
        process(transmogrifierWithoutLeaks);
        System.out.println("===========================================================");
        System.out.println("leaking memory");
        process(transmogrifierWithLeaks);
    }

    private static void process(Fun<List<String>, List<String>> transmogrifier) {
        List<List<String>> subLists = new LinkedList<>();
        int x = 0;
        for (int i = 0; i < 200; i++) {
            if (i % 20 == 0) {
                System.out.println("total memory: " + Runtime.getRuntime().totalMemory());
            }
            List<String> modifiedList = transmogrifier.apply(makeBigList());
            subLists.add(modifiedList);
            x += modifiedList.size();
        }

        System.out.println(subLists.size() + " lists contain " + x + " strings total");
    }

    private static List<String> fixedLeakSubList(List<String> strings) {
        return new LinkedList<>(leakySubList(strings));
    }

    private static List<String> leakySubList(List<String> fromList) {
        int from = RandomUtils.nextInt(fromList.size() - 1);
        int to = RandomUtils.nextInt(Math.min(fromList.size() - (from + 1), 20)) + from + 1;
        return fromList.subList(from, to);
    }

    private static List<String> makeBigList() {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 20000; i++) {
            list.add(someString());
        }

        return list;
    }

    private static String someString() {
        return RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(500) + 1);
    }
}

interface Fun<ARG, RESULT> {
    RESULT apply(ARG input);
}