package org.mahjong4j.example;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.tile.Tile;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yu1ro
 */
public class GeneralSituationInputter extends Inputter {
    private GeneralSituation situation;
    private boolean isFirstRound;
    private boolean isHoutei;
    private List<Tile> dora;
    private List<Tile> uradora;

    public GeneralSituationInputter(BufferedReader input) {
        super(input);
    }

    public GeneralSituation getSituation() {
        return situation;
    }

    @Override
    public void printHeaderComment() {
        System.out.println("====全体の状況を入力します===");
        System.out.println("最大で11項目、通常は5項目の入力が必要です");
    }

    @Override
    public void input() {
        final String firstRoundMsg = "最初の1周でこれまでに誰も鳴いていませんでしたか? 1:1周目かつ誰も鳴いていない 0:いいえ";
        if (isFirstRound = boolInputSupport(firstRoundMsg)) {
            isHoutei = false;
        } else {
            final String houteiMsg = "最後の牌で和了しましたか? 1:最後の牌 0:最後の牌ではない";
            isHoutei = boolInputSupport(houteiMsg);
        }
        Tile bakaze = fonpaiInputSupport("場風を入力して下さい");

        dora = doraInputSupport("ドラを入力して下さい");
        uradora = doraInputSupport("裏ドラを入力してください");

        situation = new GeneralSituation(isFirstRound, isHoutei, bakaze, dora, uradora);
    }

    /**
     * @param msg InputMessage
     * @return ドラのリスト
     */
    private List<Tile> doraInputSupport(String msg) {
        List<Tile> dora = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            Tile tile = tileInputSupport(msg);
            if (tile == null) {
                break;
            }
            dora.add(tile);
        }
        return dora;
    }
}
