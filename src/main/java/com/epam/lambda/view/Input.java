package com.epam.lambda.view;

import java.util.Scanner;

class Input {
    private Scanner scanner = new Scanner(System.in);

    int enterNumber() {
        return scanner.nextInt();
    }

    String enterWord() {
        return scanner.nextLine();
    }

    String enterName() {
        return scanner.next();
    }
}
