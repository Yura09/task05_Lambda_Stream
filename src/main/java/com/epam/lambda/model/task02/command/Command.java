package com.epam.lambda.model.task02.command;
@FunctionalInterface
public interface Command {
    String execute(String argument);
}
