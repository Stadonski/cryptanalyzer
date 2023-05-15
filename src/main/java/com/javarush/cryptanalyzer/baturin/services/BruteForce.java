package com.javarush.cryptanalyzer.baturin.services;



import com.javarush.cryptanalyzer.baturin.constants.Action;
import com.javarush.cryptanalyzer.baturin.constants.CryptoAlphabet;
import com.javarush.cryptanalyzer.baturin.controller.MainController;
import com.javarush.cryptanalyzer.baturin.repository.CaesarCipher;
import com.javarush.cryptanalyzer.baturin.view.ConsoleView;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BruteForce implements Functions {

    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher cipher = new CaesarCipher();
    private final CryptoAlphabet alphabet = new CryptoAlphabet();
    private final ConsoleView view = new ConsoleView();
    private final MainController controller = new MainController();

    private String path_encode_file = "encoded.txt";
    private String path_decodebrutforce_file = "output.txt";
    public String replace;
    public void execute() throws IOException {

        while (true) {
            System.out.println("Путь до файла в котором будем подбирать ключ: " + path_encode_file + "\nИзменить расположение файла? (Y/N)");
            replace = scanner.nextLine();
            if (Objects.equals(replace, "Y")) {
                String path;
                System.out.println("Введите полный путь до файла: ");
                path = scanner.nextLine();
                path_encode_file = controller.path_replacement(path);
            } else if (Objects.equals(replace, "N")) {
                break;
            } else {
                System.out.println("Не верный параметр.");
            }
        }

        while (true) {
            System.out.println("Путь до файла в котором запишется расшифрованный шифр: " + path_decodebrutforce_file + "\nИзменить расположение файла? (Y/N)");
            replace = scanner.nextLine();
            if (Objects.equals(replace, "Y")) {
                String path;
                System.out.println("Введите полный путь до файла: ");
                path = scanner.nextLine();
                path_decodebrutforce_file = controller.path_replacement(path);
            } else if (Objects.equals(replace, "N")) {
                break;
            } else {
                System.out.println("Не верный параметр.");
            }
        }

        try (var reader = Files.newBufferedReader(Paths.get(path_encode_file), StandardCharsets.UTF_8);
             var writer = Files.newBufferedWriter(Paths.get(path_decodebrutforce_file), StandardCharsets.UTF_8)) {

            StringBuilder stringBuilder = new StringBuilder();
            ArrayList<String> listStrings = new ArrayList<>();
            while (reader.ready()) {
                String string = reader.readLine();
                stringBuilder.append(string);
                listStrings.add(string);
            }
            for (int i = 0; i < alphabet.getAlphabetLenght(); i++) {
                String deEncrypt = cipher.decode(stringBuilder.toString(), i);
                if (isValidateText(deEncrypt)) {
                    for (String listString : listStrings) {
                        String encrypt = cipher.decode(listString, i);
                        writer.write(encrypt + System.lineSeparator());
                    }
                    view.printResult(Action.BRUTEFORCE, i);
                    break;
                }
            }
        }
    }

    private static boolean isValidateText(String text) {

        String textsubstring;

        if(text.length() < 30) {
            textsubstring = text;
        } else {
            textsubstring = text.substring(0, 30);
        }

        System.out.println(textsubstring);
        System.out.println("Понятен ли вам этот текст? Y/N");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        while (true) {
            if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Выберете только между Y/N");
                continue;
            }
        }
    }

}



