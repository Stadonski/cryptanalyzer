package com.javarush.cryptanalyzer.baturin.view;


import com.javarush.cryptanalyzer.baturin.constants.Action;
import com.javarush.cryptanalyzer.baturin.controller.MainController;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class ConsoleView implements View {

    private Action action;
    public ConsoleView view() throws IOException {
        MainController controller = new MainController();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(controller.getState()) {
            System.out.println("""
                    Выберете действие, введя его номер:\s
                    1. Зашифровать текст в файле с помощью ключа.\s
                    2. Расшифровать текст в файле с помощью ключа.\s
                    3. Подобрать ключ к зашифрованному тексу в файле (brute force).\s
                    4. Выхода из программы""");

            String answer = reader.readLine();

            switch (answer) {
                case "1" -> controller.action(Action.ENCODE);
                case "2" -> controller.action(Action.DECODE);
                case "3" -> controller.action(Action.BRUTEFORCE);
                case "4" -> controller.setState(false);
            }
        }
        return null;
    }

    public void printResult(Action action) {
        switch (action) {
            case DECODE -> System.out.println("Содержимое файла расшифровано.");
            case ENCODE -> System.out.println("Содержимое файла зашифровано.");
        }
    }
    public void printResult(Action action, int key) {
        if (Objects.requireNonNull(action) == Action.BRUTEFORCE) {
            System.out.println("Содержимое файла расшифровано методом перебора ключей. Ключ расшифровки K = " + key);
        }
    }
}
