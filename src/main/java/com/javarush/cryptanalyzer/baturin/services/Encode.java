package com.javarush.cryptanalyzer.baturin.services;

import com.javarush.cryptanalyzer.baturin.constants.Action;
import com.javarush.cryptanalyzer.baturin.controller.MainController;
import com.javarush.cryptanalyzer.baturin.repository.CaesarCipher;
import com.javarush.cryptanalyzer.baturin.view.ConsoleView;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class Encode implements Functions {

    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher cipher = new CaesarCipher();
    private final ConsoleView view = new ConsoleView();
    private final MainController controller = new MainController();
    private String path_original_file = "input.txt";
    private String path_encode_file = "encoded.txt";
    public String replace;
    public void execute() throws IOException {
        while (true) {
            System.out.println("Путь до файла который нужно зашифровать: " + path_original_file + "\nИзменить расположение файла? (Y/N)");
            replace = scanner.nextLine();
            if (Objects.equals(replace, "Y")) {
                String path;
                System.out.println("Введите полный путь до оригинального файла: ");
                path = scanner.nextLine();
                path_original_file = controller.path_replacement(path);
            } else if (Objects.equals(replace, "N")) {
                break;
            } else {
                System.out.println("Не верный параметр.");
            }
        }

        System.out.println("Ключ шифрования:");
        int key = Integer.parseInt(scanner.nextLine());

        while (true) {
            System.out.println("Путь до файла в котором сохранится зашифрованный текст: " + path_encode_file + "\nИзменить расположение файла? (Y/N)");
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

        try (var reader = Files.newBufferedReader(Paths.get(path_original_file), StandardCharsets.UTF_8);
             var writer = Files.newBufferedWriter(Paths.get(path_encode_file), StandardCharsets.UTF_8)
        ) {
            while (reader.ready()) {
                String string = reader.readLine();
                String encryptString = cipher.encrypt(string, key);
                writer.write(encryptString + System.lineSeparator());
            }
        }
        view.printResult(Action.ENCODE);
    }
}
