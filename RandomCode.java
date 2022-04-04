package bullscows;

import java.util.ArrayList;
import java.util.Random;

public class RandomCode extends Code {

    public RandomCode(final int LENGTH) {
        super(LENGTH);
        generateRandomCode();
    }

    // Generate unique code
    private void generateRandomCode() {
        Random random = new Random();
        var digits = new ArrayList<Character>(SIZE);
        int counter = 0;
        while (counter < SIZE) {
            char digit = (char) random.nextInt(SIZE);
            digit += digit >= 10? 'a' - 10 : '0';
            if (!digits.contains(digit)) {
                code[counter] = digit;
                digits.add(digit);
                counter++;
            }
        }
    }
}
