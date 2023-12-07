package Day5;

import util.FileReader;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Q5 {

    private static List<Long> listOfSeedNumbers = Collections.EMPTY_LIST;

    private static List<List<SeedRanges>> listOfMaps = new ArrayList<>();

    private static List<SeedRanges> inputRangesForPart2 = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("5");
        List<String> inputLines = fileReader.readFileIntoListOfStrings();
        listOfSeedNumbers = parseStringOfNumbersToList(inputLines.get(0));

        populateMappings(inputLines,true);

        Set<Long> finalDestinations =  new HashSet<>();
        for (Long currentSeed : listOfSeedNumbers) {
            long currentSeedNumber = currentSeed;


            for (List<SeedRanges> seedRanges : listOfMaps) {
                //Loop over each range and see does it fit into it
                for (SeedRanges singleSeedRange : seedRanges) {
                    //if it does, transform it based on destination
                    if (singleSeedRange.isInRange(currentSeedNumber)) {
                        currentSeedNumber = singleSeedRange.destination() + (currentSeedNumber - singleSeedRange.lowRange());
                        break;
                    }
                    //else pass on as same value
                }
            }
            finalDestinations.add(currentSeedNumber);
        }
        System.out.println(finalDestinations.stream().min(Comparator.comparingLong(Long::longValue)).get());

        // Part two,
        // Had a few ideas, none of them worked, Brute force it working from 0 until we hit a match in the starting range

        populateMappings(inputLines,false);

        inputRangesForPart2 = getRangesForPart2();
        List<List<SeedRanges>> newList = listOfMaps.reversed();

        long startingSeedNumber = 46l;
        boolean numberFound = false;

        while(!numberFound){
            long currentSeedNumber = startingSeedNumber;
            for (List<SeedRanges> seedRanges : newList) {
                //Loop over each range and see does it fit into it
                for (SeedRanges singleSeedRange : seedRanges) {
                    //if it does, transform it based on destination
                    if (singleSeedRange.isInRange(currentSeedNumber)) {
                        currentSeedNumber = singleSeedRange.destination() + (currentSeedNumber - singleSeedRange.lowRange());
                        break;
                    }
                    //else pass on as same value
                }
            }
            if(isNumberInStartingRanges(currentSeedNumber)){
                numberFound = true;
                break;
            }
            startingSeedNumber++;
        }

        System.out.println(startingSeedNumber);
    }

    private static boolean isNumberInStartingRanges(long inputNumber){
        return inputRangesForPart2.stream().anyMatch(i -> i.isInRange(inputNumber));
    }
    private static void populateMappings(List<String> inputLines,boolean part1) {
        //reset Map
        listOfMaps = new ArrayList<>();
        for(int i = 1; i < inputLines.size(); i++){

            String currentString = inputLines.get(i);

            if(currentString.contains("map")){
                List<SeedRanges> listOfSeedRanges = new ArrayList<>();

                while(!currentString.equals("") && i < inputLines.size()){
                    List<Long> currentNumbers = parseStringOfNumbersToList(currentString);
                    if(!currentNumbers.isEmpty()){
                        long destination =  currentNumbers.get(0);
                        long seedNumber = currentNumbers.get(1);
                        long mappingDiff = currentNumbers.get(2);

                        SeedRanges seedRanges;
                        if(part1){
                            seedRanges = new SeedRanges(destination,seedNumber,(seedNumber + mappingDiff) -1);
                        }else{
                            seedRanges = new SeedRanges(seedNumber,destination,(destination + mappingDiff) -1);
                        }
                        listOfSeedRanges.add(seedRanges);
                    }
                    if(i + 1 == inputLines.size()){
                        break;
                    }
                    i++;
                    currentString = inputLines.get(i);
                }
                listOfMaps.add(listOfSeedRanges);
            }
        }
    }

//    private static void populateMappingsForDestination(List<String> inputLines) {
//
//        for(int i = 1; i < inputLines.size(); i++){
//
//            String currentString = inputLines.get(i);
//
//            if(currentString.contains("map")){
//                List<SeedRanges> listOfSeedRanges = new ArrayList<>();
//
//                while(!currentString.equals("") && i < inputLines.size()){
//                    List<Long> currentNumbers = parseStringOfNumbersToList(currentString);
//                    if(!currentNumbers.isEmpty()){
//                        long destination =  currentNumbers.get(0);
//                        long seedNumber = currentNumbers.get(1);
//                        long mappingDiff = currentNumbers.get(2);
//
//                        SeedRanges seedRanges = new SeedRanges(seedNumber,destination,(destination + mappingDiff) -1);
//                        listOfSeedRanges.add(seedRanges);
//                    }
//                    if(i + 1 == inputLines.size()){
//                        break;
//                    }
//                    i++;
//                    currentString = inputLines.get(i);
//                }
//                listOfMaps.add(listOfSeedRanges);
//            }
//        }
//    }

    private static List<Long> parseStringOfNumbersToList(String stringToParse) {
        Pattern integerPattern = Pattern.compile("\\d+");
        Matcher matcher = integerPattern.matcher(stringToParse);

        List<String> numberList = new ArrayList<>();
        while (matcher.find()) {
            numberList.add(matcher.group());
        }

        return !numberList.isEmpty() ? numberList.stream().map(Long::parseLong).collect(Collectors.toList()) : Collections.EMPTY_LIST;
    }

    private static List<SeedRanges> getRangesForPart2(){
        List<SeedRanges> seedRanges = new ArrayList<>();

        for (int i = 0; i < listOfSeedNumbers.size(); i+=2) {
            seedRanges.add(new SeedRanges(-1,listOfSeedNumbers.get(i),(listOfSeedNumbers.get(i) + listOfSeedNumbers.get(i + 1)) - 1));
        }
        return seedRanges;
    }
}

record SeedRanges(long destination, long lowRange, long highRange){

    public boolean isInRange(long seedNumber){
        return seedNumber >= lowRange && seedNumber <= highRange;
    }
}
