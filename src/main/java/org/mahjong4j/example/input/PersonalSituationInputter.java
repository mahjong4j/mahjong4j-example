package org.mahjong4j.example.input;

import org.mahjong4j.PersonalSituation;
import org.mahjong4j.tile.Tile;

import java.io.BufferedReader;

/**
 * @author yu1ro
 */
public class PersonalSituationInputter extends Inputter {
    private PersonalSituation situation;

    public PersonalSituationInputter(BufferedReader input) {
        super(input);
    }

    public PersonalSituation getSituation() {
        return situation;
    }

    @Override
    public void printHeaderComment() {
        System.out.println("===個人の状況を入力します===");
        System.out.println("7項目の入力が必要です");
    }

    @Override
    public void input() {
        boolean isTsumo = boolInputSupport("ツモアガリですか? 1:ツモ 2:ロン");
        boolean isReach = boolInputSupport("リーチしましたか? 1:リーチ 0:してない");
        boolean isDoubleReach = false;
        boolean isIppatsu = false;
        if (isReach) {
            isDoubleReach = boolInputSupport("ダブリーですか? 1:ダブリー 0:ではない");
            isIppatsu = boolInputSupport("一発ですか? 1:一発 0:ではない");
        }
        boolean isChankan = boolInputSupport("槍槓ですか? 1:槍槓 0:ではない");
        boolean isRinshanKaihoh = boolInputSupport("嶺上開花ですか? 1:嶺上開花 0:ではない");
        Tile jikaze = fonpaiInputSupport("自風を入力して下さい");
        boolean isParent = jikaze == Tile.TON;
        situation = new PersonalSituation(isParent, isTsumo, isIppatsu, isReach, isDoubleReach, isChankan, isRinshanKaihoh, jikaze);
    }
}
