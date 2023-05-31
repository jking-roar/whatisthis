import java.util.Set;
import java.util.TreeSet;

public class ChallengeNine {
    public static void main(String[] args) {
        Set<String> solves3 = new TreeSet<>();
        for (String word : WordleWords.allWords) {
            char[] chars = word.toCharArray();
            if(chars[0] == chars[3] && chars[1] == chars[4] && Math.abs(chars[1] - chars[0]) == 1) {
                solves3.add(word);
            }
        }
        System.out.println(solves3.size());
        solves3.forEach(System.out::println);
    }
}
