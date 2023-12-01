package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    private String path = "src/main/java/Day%/input.txt";

    public FileReader(String inputPath,boolean usePaths) throws IOException {
        Path filePath = Paths.get(inputPath);

        //Stream
        Files.lines(filePath).forEach(System.out::println);
    }
    public FileReader(String day){
        path = path.replace("%",day);
    }

    public ArrayList<Integer> readFileIntoListOfInts() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        ArrayList<Integer> lines = new ArrayList<>();
        while(scanner.hasNextLine()) {
            lines.add(Integer.parseInt(scanner.nextLine()));
        }
        scanner.close();
        return lines;
    }

    public ArrayList<String> readFileIntoListOfStrings() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        ArrayList<String> lines = new ArrayList<>();
        while(scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        scanner.close();
        return lines;
    }
}

