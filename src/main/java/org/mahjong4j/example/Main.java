package org.mahjong4j.example;

import org.mahjong4j.Player;
import org.mahjong4j.example.input.GeneralSituationInputter;
import org.mahjong4j.example.input.HandInputter;
import org.mahjong4j.example.input.Inputter;
import org.mahjong4j.example.input.PersonalSituationInputter;
import org.mahjong4j.example.output.Outputter;
import org.mahjong4j.example.output.ScoreOutputter;
import org.mahjong4j.example.output.YakuOutputter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO:入力確認が刻子と槓子がよくわかんない
 *
 * @author yu1ro
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Mahjong4j Example!");
        System.out.println("このプログラムではMahjong4jを使って実際に役判定・点数計算ができます");

        Player player = buildPlayer();
        player.calculate();

        showResult(player);
    }

    private static Player buildPlayer() {
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        List<Inputter> inputterList = new ArrayList<>(3);

        GeneralSituationInputter generalInputter = new GeneralSituationInputter(input);
        PersonalSituationInputter personalInputter = new PersonalSituationInputter(input);
        HandInputter handInputter = new HandInputter(input);

        inputterList.add(generalInputter);
        inputterList.add(personalInputter);
        inputterList.add(handInputter);
        inputterList.forEach(inputter -> {
            inputter.printHeaderComment();
            inputter.input();
        });

        return new Player(handInputter.getHands(), generalInputter.getSituation(), personalInputter.getSituation());
    }

    private static void showResult(Player player) {
        List<Outputter> out = new ArrayList<>(2);
        out.add(new YakuOutputter(player));
        out.add(new ScoreOutputter(player));

        out.forEach(Outputter::print);
    }
}
