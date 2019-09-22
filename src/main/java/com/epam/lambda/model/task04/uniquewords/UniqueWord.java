package com.epam.lambda.model.task04.uniquewords;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
//to do
public class UniqueWord {
    private static List<String> list = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        addElements();
        System.out.println(list);
        System.out.println(numberOfUniqueWords());
        System.out.println(sortedList());
        Map<String, Long> map = countEachWord();
        for (Map.Entry<String, Long> entry : map.entrySet())
            System.out.println("word = " + entry.getKey() +
                    ", number of word in the text = " + entry.getValue());
        System.out.println(countEachChar());

    }

    private static void addElements() {
        System.out.println("Enter elements");
        while (true) {
            String word = scanner.nextLine();
            if (word.isEmpty()) {
                break;
            }
            list.add(word);
        }
    }

    private static long numberOfUniqueWords() {

        return list.stream().distinct().count();
    }

    private static List<String> sortedList() {
        return list.stream().distinct().sorted(String::compareTo).collect(Collectors.toList());
    }

    private static Map<String, Long> countEachWord() {
        return list.stream().collect(Collectors.groupingBy((x -> x), Collectors.counting()));
    }

    private static int countEachChar() {
        List<Character> fromStringToCharacter =
                list.stream()
                        .flatMapToInt(String::chars)
                        .mapToObj(i -> (char) i)
                        .filter(Character::isLowerCase)
                        .collect(Collectors.toList());
        System.out.println(fromStringToCharacter);
        return fromStringToCharacter.size();


    }
}
