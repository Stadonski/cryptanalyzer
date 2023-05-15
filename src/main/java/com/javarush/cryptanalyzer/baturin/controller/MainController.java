package com.javarush.cryptanalyzer.baturin.controller;


import com.javarush.cryptanalyzer.baturin.constants.Action;
import com.javarush.cryptanalyzer.baturin.services.BruteForce;
import com.javarush.cryptanalyzer.baturin.services.Decode;
import com.javarush.cryptanalyzer.baturin.services.Encode;


import java.io.IOException;

public class MainController {

    private Action action;
    private boolean state = true;
    public boolean getState() {
        return state;
    }
    public boolean setState(Boolean bool) {
        return this.state = bool;
    }

    public void action(Action action) throws IOException {
        switch (action) {
            case ENCODE -> new Encode().execute();
            case DECODE -> new Decode().execute();
            case BRUTEFORCE -> new BruteForce().execute();
        }
    }

    public String path_replacement(String path) {
        return path;
    }

}