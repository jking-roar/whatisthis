import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LetterFrequency {
    public static void main(String[] args) {
        Map<Character, Integer> letterWordCounts = new TreeMap<Character, Integer>() {
            @Override
            public Integer get(Object key) {
                Integer integer = super.get(key);
                if(integer == null) {
                    return 0;
                }
                return integer;
            }
        };
        WordleWords.allWords.stream().flatMap(w -> uniqueCharacters(w).stream()).forEachOrdered(character -> letterWordCounts.put(character, letterWordCounts.get(character) + 1));

        for (Map.Entry<Character, Integer> characterIntegerEntry : letterWordCounts.entrySet()) {
            System.out.printf("%s: %d%n", characterIntegerEntry.getKey(), characterIntegerEntry.getValue());
        }
    }

    private static Set<Character> uniqueCharacters(String w) {
        Set<Character> lettersInWord = new HashSet<>();
        for (Character c : w.toCharArray()) {
            lettersInWord.add(c);
        }
        return lettersInWord;
    }
}
