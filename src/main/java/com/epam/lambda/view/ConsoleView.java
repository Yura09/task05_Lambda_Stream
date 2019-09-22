package com.epam.lambda.view;

import com.epam.lambda.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView {
    private Controller controller;
    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;
    private static Scanner scanner = new Scanner(System.in);
    private Input input;
    private static Logger logger = LogManager.getLogger(ConsoleView.class);

    public ConsoleView() {
        menu = new LinkedHashMap<>();
        controller = new Controller();
        input = new Input();
        menu.put("1", " 1 - find max value among 3 numbers");
        menu.put("2", " 2 - find average value among 3 numbers");
        menu.put("3", " 3 - convert all characters of the string into upper case letter");
        menu.put("4", " 4 - print greeting message");
        menu.put("5", " 5 - print reversing message");
        menu.put("6", " 6 - generate and print list(min, max, average)");
        menu.put("7", " 7 - generate list and print sum");
        menu.put("8", " 8 - generate list and print number of value that are bigger than average");
        menu.put("Q", " Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("1", this::printMaxValue);
        methodsMenu.put("2", this::printAverageValue);
        methodsMenu.put("3", this::printUpperCaseWord);
        methodsMenu.put("4", this::printGreetingMessage);
        methodsMenu.put("5", this::printReversingMessage);
        methodsMenu.put("6", this::printListManipulation);
        methodsMenu.put("7", this::printSumOfList);
        methodsMenu.put("8", this::printNumberOfValueThatAreBiggerThanAverage);
    }

    private void showMenu() {
        System.out.println();
        logger.info("MENU:");
        for (String str : menu.values()) {
            logger.info(str);
        }
    }

    private void printMaxValue() {
        logger.info("Enter 3 numbers");
        logger.info("MAX value: " + controller.calculateMaxValue.calculate(input.enterNumber(), input.enterNumber(), input.enterNumber()));
    }

    private void printAverageValue() {
        logger.info("Enter 3 numbers");
        logger.info("AVERAGE value: " + controller.calculateAverage.calculate(input.enterNumber(), input.enterNumber(), input.enterNumber()));
    }

    private void printUpperCaseWord() {
        logger.info("Enter the word");
        logger.info("Word in the upper case: " + controller.toUpperCase.execute(input.enterWord()));
    }

    private void printGreetingMessage() {
        logger.info("Enter your Name");
        logger.info(controller.greetingMessage.execute(input.enterName()));
    }

    private void printReversingMessage() {
        logger.info("enter any message");
        logger.info("Reversing message: " + controller.reversingCommand.execute(input.enterWord()));
    }

    private void printListManipulation() {
        List<Integer> list = controller.generateList.random();
        logger.info("Generated list: " + list);
        logger.info("Average value: " + controller.averageValueOfListNumbers(list));
        System.out.println();
        logger.info("Min value: " + controller.minValue(list));
        System.out.println();
        logger.info("Max value: " + controller.maxValue(list));
    }

    private void printSumOfList() {
        List<Integer> list = controller.generateList.random();
        logger.info("Generated list: " + list);
        logger.info("Sum of list(reduce method): " + controller.sumOfListUsingReduce(list));
        System.out.println();
        logger.info("Sum of list(stream method): " + controller.sumOfListUsingStream(list));
    }

    private void printNumberOfValueThatAreBiggerThanAverage() {
        List<Integer> list = controller.generateList.random();
        logger.info("Generated list: " + list);
        logger.info("Average value: " + Math.floor(controller.averageValueOfListNumbers(list)));
        logger.info("Count: " + controller.countValueThatAreBiggerThanAverage(list));
    }

    public void show() {
        String keyMenu;
        do {
            showMenu();
            logger.info("Please, select menu point.");
            keyMenu = scanner.nextLine().toUpperCase();
            if (keyMenu.equals("Q")) {
                logger.info("bye-bye");
                break;
            }
            try {
                methodsMenu.get(keyMenu).print();
            } catch (NullPointerException e) {
                logger.error("incorrect inputs");
                break;
            }
        } while (true);
    }
}
