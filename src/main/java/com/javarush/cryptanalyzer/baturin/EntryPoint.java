package com.javarush.cryptanalyzer.baturin;

import com.javarush.cryptanalyzer.baturin.view.ConsoleView;

import java.io.IOException;

public class EntryPoint {
    public static void main(String[] args) throws IOException {
        ConsoleView console = new ConsoleView().view();
    }
}