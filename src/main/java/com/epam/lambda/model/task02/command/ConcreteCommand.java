package com.epam.lambda.model.task02.command;

public class ConcreteCommand implements Command {
    @Override
    public String execute(String argument) {
        StringBuilder reverseMessage = new StringBuilder();
        reverseMessage.append(argument);
        reverseMessage.reverse();
        return reverseMessage.toString();
    }
}
