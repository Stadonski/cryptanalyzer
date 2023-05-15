package com.javarush.cryptanalyzer.baturin.view;


import com.javarush.cryptanalyzer.baturin.constants.Action;

public interface View {
    void printResult(Action action, int key);
    void printResult(Action action);
}
