package Day8;

import util.FileReader;
import util.StringParser;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Q8 {

    private static Map<String, NextElement> mapToFollow = new HashMap<>();

    private static char[] directions;

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("8");

        List<String> listOfInstructions = fileReader.readFileIntoListOfStrings();

        directions = listOfInstructions.get(0).toCharArray();

        populateMap(listOfInstructions);

        //Part 1
        System.out.println("Part 1 : " +  getTotalStepsForInput("AAA",true));


        // Part 2
        List<String> listOfElementsToCheck = mapToFollow.keySet().stream()
                .filter(input -> input.charAt(2) == 'A')
                .collect(Collectors.toList());

        List<Long> stepsTakenList = new ArrayList<>();

        for (String nextElementToCheck : listOfElementsToCheck) {
            stepsTakenList.add(getTotalStepsForInput(nextElementToCheck,false));
        }
        System.out.println("Part 2 : " + stepsTakenList.stream().reduce(1l,(a,b) -> lowestCommonMultiple(a,b)));
    }

    private static long getTotalStepsForInput(String nextElementToCheck,boolean part1){

        long totalSteps = 0;
        for (int currentInstuction= 0; currentInstuction < directions.length; currentInstuction++) {
            //Exit points for part 1 / 2
            if(part1){
                if(nextElementToCheck.equals("ZZZ")){
                    return totalSteps;
                }
            }else{
                if (nextElementToCheck.charAt(2) == 'Z') {
                    return totalSteps;
                }
            }

            if(directions[currentInstuction] == 'L'){
                nextElementToCheck = mapToFollow.get(nextElementToCheck).left();
            }else{
                nextElementToCheck = mapToFollow.get(nextElementToCheck).right();
            }
            totalSteps++;
            //loop around instructions
            if(currentInstuction + 1 == directions.length){
                currentInstuction = -1;
            }
        }
        return totalSteps;
    }

    private static void populateMap(List<String> listOfInstructions) {
        for (String inputLine : listOfInstructions) {
            List<String> nextElementParse = StringParser.extractSubStringsFromStringToList(inputLine);
            //Avoid First Line / Empty lines
            if (nextElementParse.size() > 1) {
                mapToFollow.put(nextElementParse.get(0), new NextElement(nextElementParse.get(1), nextElementParse.get(2)));
            }
        }
    }

    //Ripped the below formula right outta Chat GPT lol
    static long greatestCommonDenominator(long a, long b)
    {
        while (b != 0)
        {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    static long lowestCommonMultiple(long a, long b)
    {
        return (a / greatestCommonDenominator(a, b)) * b;
    }

}

record NextElement(String left, String right) {}
