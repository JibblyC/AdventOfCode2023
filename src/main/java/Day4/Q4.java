package Day4;

import util.FileReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Q4 {

    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("4");

        List<String> listOfCardGames = fileReader.readFileIntoListOfStrings();

        HashMap<Integer, Integer> amountOfCards = new HashMap<>();

        for(String game : listOfCardGames){
            int gameNumber = Integer.parseInt(game.substring(game.indexOf(' '), game.indexOf(':')).trim());
            HashSet<String> winningNumbers = new HashSet<>(Arrays.asList(game.substring(game.indexOf(':') + 1, game.indexOf('|')).split(" ")));
            winningNumbers.removeIf(item -> item.equals(""));

            HashSet<String> drawnNumbers = new HashSet<>(Arrays.asList(game.substring(game.indexOf('|') + 1).split(" ")));
            drawnNumbers.removeIf(item -> item.equals(""));

            drawnNumbers.retainAll(winningNumbers);

            amountOfCards.merge(gameNumber,1,Integer::sum);

            int cardsToAdd = drawnNumbers.size();

            if(cardsToAdd > 0){
                int timesToLoop = amountOfCards.get(gameNumber);

                for (int i = 0; i < timesToLoop; i++) {
                    //Start at 1 as we dont want to update starting game
                    for (int j = 1; j <= cardsToAdd; j++) {
                        int gameToIncrease = gameNumber + j;
                        amountOfCards.merge(gameToIncrease,1,Integer::sum);
                    }
                }
            }

        }

        System.out.println(amountOfCards.values().stream().mapToInt(Integer::intValue).sum());
    }
}
