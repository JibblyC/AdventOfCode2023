package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileReader {

    private String path = "src/main/java/Day%/input.txt";

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

    public List<String> readFileIntoListOfStrings() throws IOException {
        Path filePath = Paths.get(path);
        return Files.lines(filePath)
                .collect(Collectors.toList());
    }
}

