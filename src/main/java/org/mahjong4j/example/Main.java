package org.mahjong4j.example;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * TODO:手牌入力
 * TODO:結果出力
 * @author yu1ro
 */
public class Main {
    public static void main(String[] args) {
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to Mahjong4j Example!");
        GeneralSituationInputter generalInputter = new GeneralSituationInputter(input);
        PersonalSituationInputter personalInputter = new PersonalSituationInputter(input);

        generalInputter.input();
        personalInputter.input();

        GeneralSituation generalSituation = generalInputter.getSituation();
        PersonalSituation personalSituation = personalInputter.getSituation();

    }
}
