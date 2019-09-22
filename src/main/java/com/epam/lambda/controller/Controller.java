package com.epam.lambda.controller;

import com.epam.lambda.model.task01.calculate.Calculate;
import com.epam.lambda.model.task02.command.Command;
import com.epam.lambda.model.task02.command.ConcreteCommand;
import com.epam.lambda.model.task03.streams.RandomList;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    public Calculate calculateMaxValue = (x, y, z) -> Math.max(x, Math.max(y, z));
    public Calculate calculateAverage = (x, y, z) -> (x + y + z) / 3;
    public Command toUpperCase = String::toUpperCase;
    public Command greetingMessage = new Command() {
        @Override
        public String execute(String name) {
            return "Hello " + name + " nice to meet you";
        }
    };
    public Command reversingCommand = new ConcreteCommand();

    public RandomList generateList = () -> {
        List<Integer> list = new ArrayList<>();
        randomGenerator(list);
        return list;
    };

    private void randomGenerator(List<Integer> list) {
        Random random = new Random();
        for (int i = 0; i < 11; i++) {
            list.add(random.nextInt(40));
        }
    }

    public int sumOfListUsingReduce(List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }

    public int sumOfListUsingStream(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    public double averageValueOfListNumbers(List<Integer> list) {
        OptionalDouble averageValue = list.stream().mapToInt(x -> x).average();
        double absentValue = 0.0;
        return averageValue.isPresent() ? averageValue.getAsDouble() : absentValue;
    }

    public int minValue(List<Integer> list) {
        Optional<Integer> minValue = list.stream().min(Comparator.comparing(Integer::intValue));
        int absentValue = Integer.MIN_VALUE;
        return minValue.orElse(absentValue);
    }

    public int maxValue(List<Integer> list) {
        return list.stream().collect(Collectors.summarizingInt(value -> value)).getMax();
    }

    public long countValueThatAreBiggerThanAverage(List<Integer> list) {
        return list.stream().filter(value -> value > averageValueOfListNumbers(list)).count();
    }
}
