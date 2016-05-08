package org.mahjong4j.example;

import org.mahjong4j.Player;
import org.mahjong4j.yaku.normals.NormalYaku;
import org.mahjong4j.yaku.yakuman.Yakuman;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO:ドラは表示牌ではなく、実際のドラを入力してください
 * TODO:リーチしてないのにダブリーと一発なのか聞くのよくない
 * @author yu1ro
 */
public class Main {
    public static void main(String[] args) {
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to Mahjong4j Example!");
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

        Player player = new Player(handInputter.getHands(), generalInputter.getSituation(), personalInputter.getSituation());
        player.calculate();

        System.out.println("player.getFu() = " + player.getFu());
        System.out.println("player.getHan() = " + player.getHan());
        for (Yakuman yakuman : player.getYakumanList()) {
            System.out.println("yakuman = " + yakuman);
        }
        for (NormalYaku yaku : player.getNormalYakuList()) {
            System.out.println("yaku = " + yaku);
        }
        System.out.println("player.getScore() = " + player.getScore());
    }
}
