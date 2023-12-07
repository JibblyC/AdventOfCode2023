package Day6;

import util.FileReader;
import util.StringParser;

import java.io.IOException;
import java.util.List;

public class Q6 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("6");
        List<String> inputLines = fileReader.readFileIntoListOfStrings();

        List<Long> listOfTimes = StringParser.extractNumbersFromStringToList(inputLines.get(0));
        List<Long> listOfTargets = StringParser.extractNumbersFromStringToList(inputLines.get(1));

        int totalPower = 1;

        for (int currentRace = 0; currentRace < listOfTimes.size(); currentRace++) {

            totalPower *= timesBeatenTargetTime(listOfTimes.get(currentRace),listOfTargets.get(currentRace));;
        }

        System.out.println(totalPower);

        // part 2

        long currentTarget = Long.parseLong(StringParser.extractNumbersFromStringToOneBigNumberString(inputLines.get(1)));
        long currentTime = Long.parseLong(StringParser.extractNumbersFromStringToOneBigNumberString(inputLines.get(0)));


        System.out.println(timesBeatenTargetTime(currentTime,currentTarget));
    }

    private static long timesBeatenTargetTime(long currentTime, long currentTarget){

        var beatenTheTargetCount = 0;

        for (int press = 0; press < currentTime; press++) {

            long totalDistance = press * (currentTime - press);

            if(totalDistance > currentTarget){
                beatenTheTargetCount ++;
            }
        }
        return beatenTheTargetCount;
    }
}
