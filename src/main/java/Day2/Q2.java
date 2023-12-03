package Day2;

import util.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Q2 {

    private static final HashMap<String, Integer> mapOfColours = new HashMap<>();

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("2");

        List<String> inputLines = fileReader.readFileIntoListOfStrings();

        initalizeMaxColourMap();

        part1(inputLines);

        part2(inputLines);
    }

    private static void part2(List<String> inputLines) {
        int sumOfPowers = 0;

        for (String line : inputLines) {
            List<String> listOfResults = sanatizeData(line);



            HashMap<String, Integer> largestNumberFound = new HashMap<>();

            for (int i = 2; i < listOfResults.size(); i = i + 2) {
                String colour = listOfResults.get(i + 1);
                int number = Integer.parseInt(listOfResults.get(i));

                largestNumberFound.putIfAbsent(colour, 0);

                if (largestNumberFound.get(colour) < number) {
                    largestNumberFound.put(colour, number);
                }
            }

            sumOfPowers += largestNumberFound.values().stream().reduce(1, (a, b) -> a * b);
        }
        System.out.println("Sum of powers : " + sumOfPowers);
    }


    private static void part1(List<String> inputLines) {


        int sumOfValidGames = 0;
        for (String line : inputLines) {
            List<String> listOfResults = sanatizeData(line);


            //Start on 3rd index to rule out Game string
            boolean validGame = true;
            for (int i = 2; i < listOfResults.size(); i = i + 2) {
                String colour = listOfResults.get(i + 1);
                int number = Integer.parseInt(listOfResults.get(i));

                if (mapOfColours.get(colour) < number) {
                    validGame = false;
                    break;
                }
            }
            if (validGame) {
                sumOfValidGames += Integer.parseInt(listOfResults.get(1));
            }

        }
        System.out.println("Sum of valid games : " + sumOfValidGames);


    }

    private static void initalizeMaxColourMap() {
        mapOfColours.put("red", 12);
        mapOfColours.put("green", 13);
        mapOfColours.put("blue", 14);
    }

    private static List<String> sanatizeData(String line){
        return Stream.of(line.split("\\s+"))
                .map(String::trim)
                .map(s -> s.replace(",", ""))
                .map(s -> s.replace(";", ""))
                .map(s -> s.replace(":", ""))
                .collect(Collectors.toList());
    }
}


