package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringParser {

    public StringParser() {
    }

    public static List<Long> extractNumbersFromStringToList(String stringToParse) {
        Pattern integerPattern = Pattern.compile("\\d+");
        Matcher matcher = integerPattern.matcher(stringToParse);

        List<String> numberList = new ArrayList<>();
        while (matcher.find()) {
            numberList.add(matcher.group());
        }

        return !numberList.isEmpty() ? numberList.stream().map(Long::parseLong).collect(Collectors.toList()) : Collections.EMPTY_LIST;
    }

    public static List<String> extractSubStringsFromStringToList(String stringToParse) {
        Pattern integerPattern = Pattern.compile("\\w+");
        Matcher matcher = integerPattern.matcher(stringToParse);

        List<String> numberList = new ArrayList<>();
        while (matcher.find()) {
            numberList.add(matcher.group());
        }

        return numberList;
    }

    public static String extractNumbersFromStringToOneBigNumberString(String stringToParse) {
        Pattern integerPattern = Pattern.compile("\\d+");
        Matcher matcher = integerPattern.matcher(stringToParse);

        List<String> numberList = new ArrayList<>();
        while (matcher.find()) {
            numberList.add(matcher.group());
        }

        return !numberList.isEmpty() ? numberList.stream().collect(Collectors.joining()) : "";
    }



}
