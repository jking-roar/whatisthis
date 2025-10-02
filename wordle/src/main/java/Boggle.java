import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Boggle {

    private final Die[] DICE = {
            new Die("AAEEGN", 0),
            new Die("ABBJOO", 0),
            new Die("ACHOPS", 0),
            new Die("AFFKPS", 0),
            new Die("AOOTTW", 0),
            new Die("CIMOTU", 0),
            new Die("DEILRX", 0),
            new Die("DELRVY", 0),
            new Die("DISTTY", 0),
            new Die("EEGHNW", 0),
            new Die("EEINSU", 0),
            new Die("EHRTVW", 0),
            new Die("EIOSST", 0),
            new Die("ELRTTY", 0),
            new Die("HIMNUQ", 0),
            new Die("HLNNRZ", 0),
            new Die("AAEEGN", 0),
            new Die("ABBJOO", 0),
            new Die("ACHOPS", 0),
            new Die("AFFKPS", 0),
            new Die("AOOTTW", 0),
            new Die("CIMOTU", 0),
            new Die("DEILRX", 0),
            new Die("DELRVY", 0),
            new Die("DISTTY", 0),
            new Die("EEGHNW", 0),
            new Die("EEINSU", 0),
            new Die("EHRTVW", 0),
            new Die("EIOSST", 0),
            new Die("ELRTTY", 0),
            new Die("HIMNUQ", 0),
            new Die("HLNNRZ", 0)
    };
    Die[][] board = new Die[4][4];
    int attempts = 0;

    Boggle() {
        shuffleBoard();
    }

    private void shuffleBoard() {
        List<Die> dice = new ArrayList<>(Arrays.asList(DICE));
        setDiceDown(dice);
    }

    public void printBoard() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Die[] die : board) {
            for (Die d : die) {
                stringBuilder.append(d.getShowingLetter()).append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    public boolean shuffleBoardToSpellHopefully(String word) {
        // get characters
        char[] chars = word.toCharArray();
        List<Die> diceAvailable = new ArrayList<>(Arrays.asList(DICE));
        Collections.shuffle(diceAvailable);
        List<Die> diceUsed = new ArrayList<>();
        boolean found = false;
        for (char aChar : chars) {
            found = false;
            for (int i = 0; i < diceAvailable.size(); i++) {

                Die die = diceAvailable.get(i);
                boolean hasLetter = die.setTo(aChar);
                if (hasLetter) {
                    diceUsed.add(die);
                    diceAvailable.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                if (attempts < 10) {
                    attempts++;
                    found = shuffleBoardToSpellHopefully(word);
                } else {
                    break;
                }

            }
        }

        // shuffle dice
        for (Die die : diceAvailable) {
            die.shuffle();
        }
        diceUsed.addAll(diceAvailable);
        setDiceDown(diceUsed);
        return found;
    }

    private void setDiceDown(List<Die> diceUsed) {
        for (int i = 0; i < 16; i++) {
            int row = i / 4;
            int col = i % 4;
            Die die = diceUsed.get(i);
            board[row][col] = die;
        }
    }

    static class Die {
        final String letters;
        int face;

        Die(String letters, int face) {
            this.letters = letters;
            this.face = face;
        }

        void shuffle() {
            face = (int) (Math.random() * 6);
        }

        public boolean setTo(char aChar) {
            int index = letters.indexOf(aChar);
            if (index != -1) {
                face = index;
                return true;
            }
            return false;
        }

        public char getShowingLetter() {
            return letters.charAt(face);
        }

        public String toString() {
            return "(" + getShowingLetter() + ")" + " " + letters;
        }
    }

    public static void main(String[] args) {
        boolean allLettersPlaced;
        for (int i = 0; i < 30; i++) {
            Boggle boggle = new Boggle();
            //reverse of ABCDEFGHIJKLMNOPQRS
            // is SRQPONMLKJIHGFEDCBA
            allLettersPlaced = boggle.shuffleBoardToSpellHopefully("CANNIBALOEDRUK");
            if (!allLettersPlaced) {
                System.out.println("Could not shuffle board to spell word");
            }
            boggle.printBoard();
            if (allLettersPlaced) {
                break;
            }
        }
    }
}
