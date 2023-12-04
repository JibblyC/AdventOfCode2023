package Day3;

import util.FileReader;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Q3 {

    private static final List<Location> locations = new ArrayList<>();

    private static List<List<Character>> mapOfInput;

    private static int maxRow = 0;
    private static int maxCol= 0;



    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("3");

        List<String> inputLines = fileReader.readFileIntoListOfStrings();
        mapOfInput = inputLines.stream()
                        .map(String::chars)
                        .map(intStream -> intStream
                                        .mapToObj(e -> (char)e)
                                        .collect(Collectors.toList()))
                        .collect(Collectors.toList());

        initalizeMovementList();

        maxCol = mapOfInput.size();
        maxRow = mapOfInput.get(0).size();

        var totalSumofParts = 0;
        var totalPowers = 0;


        Map<Location,List<String>> numbersTouchingGears = new HashMap<>();

        for (int row = 0; row < mapOfInput.size(); row++) {
            for (int column = 0; column < mapOfInput.get(row).size(); column++) {

                //Check if we land of a number
                if (Character.isDigit(mapOfInput.get(row).get(column))) {

                    Character currentValue = mapOfInput.get(row).get(column);
                    int currentColvalue = column;
                    StringBuilder currentNumber = new StringBuilder();

                    //Is Valid part for part 1
                    boolean isValidPartCheck = false;

                    //Gear Locations for Part 2
                    HashSet<Location> gearLocations = new HashSet<>();

                    //Build out rest of the number
                    while (Character.isDigit(currentValue)) {
                        Location location = new Location(currentColvalue, row);
                        currentNumber.append(currentValue);

                        if (currentColvalue + 1 == maxRow) {
                            break;
                        }
                        currentValue = mapOfInput.get(row).get(++currentColvalue);

                        //Add all gear locations around current number to set
                        gearLocations.addAll(getGearLocations(location));

                        if(!isValidPartCheck){
                            isValidPartCheck = isValidPart(location);
                        }
                    }

                    //Only sum the number if it is a valid part
                    if(isValidPartCheck){
                        totalSumofParts += Integer.parseInt(currentNumber.toString());
                    }

                    for(Location location : gearLocations){
                        numbersTouchingGears.computeIfAbsent(location, k -> new ArrayList<>()).add(currentNumber.toString());
                    }

                    column += currentNumber.length() - 1;
                }
            }
        }


        //Loop over Gear locations, any that only have two numbers attached, power and sum
        for(List<String> numbers : numbersTouchingGears.values()){
            if(numbers.size() == 2){
                var multiply = numbers.stream()
                        .map(Integer::parseInt)
                        .reduce(1,(a,b) -> a * b);

                totalPowers += multiply;
            }
        }

        System.out.println(totalSumofParts);
        System.out.println(totalPowers);



    }

    private static boolean isValidPart(Location currentLocation){
        for(Location location : locations){
            Location checkLocation = new Location(location.col() + currentLocation.col(), location.row() + currentLocation.row());
            if(isWithinBounds(checkLocation)){
                char charToCheck = mapOfInput.get(checkLocation.row()).get(checkLocation.col());
                if(!Character.isDigit(charToCheck) && !(charToCheck == '.')){
                    return true;
                }
            }
        }
        return false;
    }

    private static HashSet<Location> getGearLocations(Location currentLocation){
        HashSet<Location> setToReturn = new HashSet<>();
        for(Location location : locations){
            Location checkLocation = new Location(location.col() + currentLocation.col(), location.row() + currentLocation.row());
            if(isWithinBounds(checkLocation)){
                char charToCheck = mapOfInput.get(checkLocation.row()).get(checkLocation.col());
                if(charToCheck =='*'){
                    setToReturn.add(checkLocation);
                }
            }
        }
        return setToReturn;
    }

    private static boolean isWithinBounds(Location checkLocation) {
        return checkLocation.row() < maxRow
                && checkLocation.col() < maxCol
                && checkLocation.col() > 0
                && checkLocation.row() > 0;
    }


    private static void initalizeMovementList(){
        locations.add(new Location(1, 0));
        locations.add(new Location(-1, 0));
        locations.add(new Location(0, 1));
        locations.add(new Location(0, -1));
        locations.add(new Location(1, 1));
        locations.add(new Location(1, -1));
        locations.add(new Location(-1, 1));
        locations.add(new Location(-1, -1));
    }
}

record Location(int col, int row){}
