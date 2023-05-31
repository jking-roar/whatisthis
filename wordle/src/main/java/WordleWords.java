import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WordleWords {
    public static final List<String> solutions = loadWordsFrom("solutions.txt");

    private static List<String> loadWordsFrom(String filePath) {
        try {
            return Files.readAllLines(Paths.get(ClassLoader.getSystemResource(filePath).toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("oh no", e);
        }
    }

    public static final List<String> otherWords = loadWordsFrom("otherWords.txt");

    public static final List<String> allWords = buildAll();

    private static List<String> buildAll() {
        ArrayList<String> words = new ArrayList<>(solutions.size() + otherWords.size());
        words.addAll(solutions);
        words.addAll(otherWords);
        return Collections.unmodifiableList(words);
    }
}
