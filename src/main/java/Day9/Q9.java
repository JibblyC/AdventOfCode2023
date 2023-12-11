package Day9;

import util.FileReader;
import util.StringParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Q9 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("9");

        List<String> listOfReadings = fileReader.readFileIntoListOfStrings();

        long totalSumPart1 = 0l;
        long totalSumPart2 = 0l;

        for(String inputLine : listOfReadings){

            List<Long> numberList = StringParser.extractNumbersFromStringToList(inputLine);

            List<List<Long>> listsOfNumbersLists =  new ArrayList<>();

            listsOfNumbersLists.add(numberList);

            while(!isListAllZeros(listsOfNumbersLists.getLast())){

                List<Long> currentListToIterateOver = listsOfNumbersLists.getLast();

                List<Long> generateNextLevel =  new ArrayList<>();

                for (int currentIndex = 0; currentIndex < currentListToIterateOver.size() - 1; currentIndex++) {
                    var currentNumber = currentListToIterateOver.get(currentIndex);
                    var nextNumber = currentListToIterateOver.get(currentIndex + 1);

                    generateNextLevel.add(differenceBetweenTwoNumbers(currentNumber,nextNumber));
                }

                listsOfNumbersLists.add(generateNextLevel);
            }


            List<List<Long>> listsOfNumbersLists2 = listsOfNumbersLists.reversed();
            var part2placeholder = 0l;
            for (int i = 0; i < listsOfNumbersLists2.size(); i++) {
                totalSumPart1 += listsOfNumbersLists2.get(i).getLast();

                part2placeholder = differenceBetweenTwoNumbers(part2placeholder,listsOfNumbersLists2.get(i).getFirst());
            }

            totalSumPart2 += part2placeholder;
        }

        System.out.println(totalSumPart1);
        System.out.println(totalSumPart2);
    }

    private static long differenceBetweenTwoNumbers(long x, long y){

        return y - x;
    }

    private static boolean isListAllZeros(List<Long> input){
        return input.stream().allMatch(num -> num == 0);
    }
}


