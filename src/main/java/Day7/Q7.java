package Day7;

import util.FileReader;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Q7 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("7");

        List<String> listOfDraws = fileReader.readFileIntoListOfStrings();

        Part1(listOfDraws);

        Part2(listOfDraws);


    }

    private static void Part2(List<String> listOfDraws) {
        List<CardsDrawn> cardsDrawnsList = new ArrayList<>();
        for(String input : listOfDraws){
            String[] split = input.split(" ");
            CardsDrawn cardsDrawn = new CardsDrawn(split[0],Long.parseLong(split[1]));
            cardsDrawn.setCamelHand(workoutCamelHand(cardsDrawn.getDraw()));

            if(cardsDrawn.getDraw().contains("J")){
                cardsDrawn = findBestPossibleCamelHand(cardsDrawn);
            }
            cardsDrawnsList.add(cardsDrawn);
        }

        cardsDrawnsList.sort(Comparator.comparing(o -> o));

        long totalSum = 0;
        for (int i = 0; i < cardsDrawnsList.size(); i++) {
            totalSum += (cardsDrawnsList.get(i).getBid() * (i + 1));
        }

        System.out.println("Part 2 total sum : " + totalSum);

    }

    private static void Part1(List<String> listOfDraws) {

        List<CardsDrawn> cardsDrawnsList = new ArrayList<>();
        for(String input : listOfDraws){
            String[] split = input.split(" ");
            CardsDrawn cardsDrawn = new CardsDrawn(split[0],Long.parseLong(split[1]));
            cardsDrawn.setCamelHand(workoutCamelHand(cardsDrawn.getDraw()));
            cardsDrawnsList.add(cardsDrawn);
        }

        cardsDrawnsList.sort(Comparator.comparing(o -> o));

        long totalSum = 0;
        for (int i = 0; i < cardsDrawnsList.size(); i++) {
            totalSum += (cardsDrawnsList.get(i).getBid() * (i + 1));
        }

        System.out.println("Part 1 total sum : " + totalSum);
    }

    private static CardsDrawn findBestPossibleCamelHand(CardsDrawn cardToCheck){
            List<Character> existingCharacters =  getUniqueCharacters(cardToCheck.getDraw());
            List<CardsDrawn> newDrawsCreated = new ArrayList<>();

            for(char charToswap : existingCharacters){
                String swappedOutValues = cardToCheck.getDraw().replace('J',charToswap);
                CardsDrawn cardsDrawn = new CardsDrawn(swappedOutValues, cardToCheck.getBid());
                cardsDrawn.setCamelHand(workoutCamelHand(cardsDrawn.getDraw()));
                newDrawsCreated.add(cardsDrawn);
            }

            newDrawsCreated.sort(Comparator.comparing(o -> o));
            cardToCheck.setCamelHand(newDrawsCreated.getLast().getCamelHand());

            return cardToCheck;
    }

    private static List<Character> getUniqueCharacters(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .distinct()
                .collect(Collectors.toList());
    }



    public static CamelHand workoutCamelHand(String drawToCalculate){
        Map<Character, Integer> countInstances = new HashMap<>();

        CamelHand camelHandToReturn = CamelHand.HIGH_CARD;

        for(char indv : drawToCalculate.toCharArray()){
            countInstances.put(indv, countInstances.getOrDefault(indv, 0) + 1);
        }
        if(countInstances.containsValue(5)){
            camelHandToReturn = CamelHand.FIVE_OF_A_KIND;
        }
        else if(countInstances.containsValue(4)){
            camelHandToReturn = CamelHand.FOUR_OF_A_KIND;
        }
        else if(countInstances.containsValue(3) && countInstances.containsValue(2)){
            camelHandToReturn = CamelHand.FULL_HOUSE;
        }
        else if(countInstances.containsValue(3)){
            camelHandToReturn = CamelHand.THREE_OF_A_KIND;
        }else{
            long countTwos = countInstances.values().stream().filter(count -> count == 2).count();
            if(countTwos == 2){
                camelHandToReturn = CamelHand.TWO_PAIR;
            }else if(countTwos == 1){
                camelHandToReturn = CamelHand.ONE_PAIR;
            }
        }
        return camelHandToReturn;
    }

}


