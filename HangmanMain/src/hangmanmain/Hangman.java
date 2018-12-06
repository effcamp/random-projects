package hangmanmain;

import java.util.Scanner;

/**
 *
 * @author Fred Campos
 */
public class Hangman {

    private String word = "";
    private StringBuilder wordBlank = new StringBuilder("");
    private String letter;
    private String guessedLetters = "";
    private int guesses = 0;
    private StringBuilder hangmanBody = new StringBuilder("      ");
    private Scanner input = new Scanner(System.in);

    public Hangman() {

    }

    public void startGame() {
        System.out.println("Word to play:");
        word = input.nextLine().toUpperCase();
        // Empty terminal space so you can't see the word.
        System.out.println("\n\n\n\n\n\n\n");
        // Create the empty spaces with underlines.
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ' ') {
                wordBlank.append(" ");
            } else {
                wordBlank.append("_");
            }
        }
        boolean gameOver = false;
        // Game loop
        while (!gameOver) {

            System.out.print(drawGame());
            letter = input.nextLine().toUpperCase();
            System.out.println("\n\n\n\n\n\n");
            // Checks if user enterd a single letter (no digits), and checks 
            // if it has already been entered
            if (letter.length() == 1 && letter.toUpperCase().matches("[A-Z]")
                    && guessedLetters.indexOf(letter) == -1) {
                // Add letter to guessed letters string
                guessedLetters += letter + " ";
                // Wrong guesses adds body parts to hangman
                if (word.indexOf(letter) == -1) {
                    guesses++;
                    wrongGuess();
                } else {
                    // Good guesses add the letter to the word string
                    int index = 0;
                    while (index != -1) {
                        index = word.indexOf(letter, index);
                        if (index != -1) {
                            wordBlank.setCharAt(index, letter.charAt(0));
                            index++;
                        }
                    }
                }

            }
            // Check to see if you win or lose by comparing against the string, 
            // or you have guessed 6 times
            if (wordBlank.toString().equalsIgnoreCase(word) || guesses == 6) {
                System.out.println(drawGame());
                gameOver = true;
                System.out.print("Game over ---- ");
                if (guesses == 6) {
                    System.out.println(" You lose!\n"
                            + "The correct answer was " + word);

                } else {
                    System.out.println("You Win!");
                }
            }

        }

    }

    /**
     *
     * @author Fred Campos
     *
     * Draws the game
     */
    public String drawGame() {
        String prompt = guesses == 6 ? "" : "Letter guess:";
        return String.format("HANGMAN -------------------\n"
                + " %c \n"
                + "%c%c%c \n"
                + " %c%c\n"
                + "Word: " + wordBlank + "     Guessed letters: " + guessedLetters
                + "\n%s",
                hangmanBody.charAt(0),
                hangmanBody.charAt(1),
                hangmanBody.charAt(2),
                hangmanBody.charAt(3),
                hangmanBody.charAt(4),
                hangmanBody.charAt(5),
                prompt
        );
    }

    /**
     *
     * @author Fred Campos
     *
     */
    public void wrongGuess() {
        switch (guesses) {
            case 1:
                hangmanBody.setCharAt(guesses - 1, 'O');
                break;
            case 2:
                hangmanBody.setCharAt(guesses - 1, '/');
                break;
            case 3:
                hangmanBody.setCharAt(guesses - 1, '|');
                break;
            case 4:
                hangmanBody.setCharAt(guesses - 1, '\\');
                break;
            case 5:
                hangmanBody.setCharAt(guesses - 1, '/');
                break;
            case 6:
                hangmanBody.setCharAt(guesses - 1, '\\');
                break;
        }
    }

}
