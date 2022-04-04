package bullscows;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static boolean makeMove(final char[] code, final char[] userInput) {
        var userCode = new ArrayList<Character>(code.length);
        int bulls = 0, cows = 0;

        for (int i = 0; i < code.length; i++) {
            userCode.add(userInput[i]);
        }

        for (int i = 0; i < code.length; i++) {
            if (code[i] == userCode.get(i)) {
                bulls++;
            } else if (userCode.contains(code[i])) {
                cows++;
            }
        }

        String results;
        if (cows + bulls == 0) {
            results = "None";
        } else if (bulls == code.length || cows == 0) {
            results = bulls + " bull(s)";
        } else {
            if (bulls == 0) {
                results = cows + " cow(s)";
            } else {
                results = bulls + " bull(s) and " + cows + " cow(s)";
            }
        }

        System.out.printf("Grade: %s.%n", results);
        return bulls == code.length;
    }

    public static void main(String[] args) {
        final int LENGTH;
        final int NUMBER_OS_SYMBOLS;
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Input the length of the secret code: ");
            LENGTH = scanner.nextInt();
            if (LENGTH <= 0) {
                throw new Throwable(String.valueOf(LENGTH));
            }
            System.out.print("Input the number of possible symbols in the code: ");
            NUMBER_OS_SYMBOLS = scanner.nextInt();
        } catch (Exception e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", scanner.next());
            return;
        } catch (Throwable e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", e.getMessage());
            return;
        }
        if (NUMBER_OS_SYMBOLS <= 0 || NUMBER_OS_SYMBOLS > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        } else if (LENGTH > NUMBER_OS_SYMBOLS) {
            System.out.printf(
                    "Error: it's not possible to generate a code with a length of %d with %d unique symbols.%n",
                    LENGTH,
                    NUMBER_OS_SYMBOLS
            );
            return;
        }

        final String availableChars;
        if (NUMBER_OS_SYMBOLS < 10) {
            availableChars = "0-" + NUMBER_OS_SYMBOLS;
        } else {
            availableChars = "0-9, a-" + (char)('a' - 11 + NUMBER_OS_SYMBOLS);
        }
        System.out.printf("The secret is prepared: %s (%s).%n", "*".repeat(LENGTH), availableChars);
        System.out.println("Okay, let's start a game!");

        RandomCode randomCode = new RandomCode(LENGTH);
        int turns = 1;
        do {
            System.out.printf("Turn %d:%n", turns);
            turns++;
        } while(!makeMove(randomCode.code, scanner.next().toCharArray()));
        System.out.println("Congratulations! You guessed the secret code.");
    }
}
