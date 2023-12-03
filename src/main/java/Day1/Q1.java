package Day1;

import util.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q1 {

    private static final HashMap<String, Integer> stringToIntValues = new HashMap<>();

    private static final String validNumberStartingChars = "otfsen";

    public static void main(String[] args) throws IOException {
        createNumberMap();
        FileReader fileReader = new FileReader("1");
        List<String> inputLines = fileReader.readFileIntoListOfStrings();

        int sum = 0;
        for (String line : inputLines) {
            ArrayList<String> numbers = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                char currentCharacter = line.charAt(i);

                if (Character.isDigit(currentCharacter)) {
                    numbers.add(String.valueOf(currentCharacter));
                }

                if (isValidNumberString(currentCharacter)) {
                    String searchForwardOnly = line.substring(i);

                    for (String key : stringToIntValues.keySet()) {
                        if (searchForwardOnly.indexOf(key) == 0) {
                            numbers.add(String.valueOf(stringToIntValues.get(key)));
                            break;
                        }
                    }
                }

            }
            int combinedValue = Integer.parseInt((numbers.getFirst() + numbers.getLast()));
            sum += combinedValue;
            System.out.println(combinedValue);
        }

        System.out.println(sum);

    }

    private static boolean isValidNumberString(char inputChar) {
        return validNumberStartingChars.contains(String.valueOf(inputChar));

    }

    private static void createNumberMap() {
        stringToIntValues.put("one", 1);
        stringToIntValues.put("two", 2);
        stringToIntValues.put("three", 3);
        stringToIntValues.put("four", 4);
        stringToIntValues.put("five", 5);
        stringToIntValues.put("six", 6);
        stringToIntValues.put("seven", 7);
        stringToIntValues.put("eight", 8);
        stringToIntValues.put("nine", 9);
    }
}


