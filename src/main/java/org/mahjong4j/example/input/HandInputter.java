package org.mahjong4j.example.input;

import org.mahjong4j.IllegalMentsuSizeException;
import org.mahjong4j.IllegalShuntsuIdentifierException;
import org.mahjong4j.MahjongTileOverFlowException;
import org.mahjong4j.hands.*;
import org.mahjong4j.tile.Tile;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yu1ro
 */
public class HandInputter extends Inputter {
    private Hands hands;

    public HandInputter(BufferedReader input) {
        super(input);
    }

    public Hands getHands() {
        return hands;
    }

    @Override
    public void printHeaderComment() {
        System.out.println("===手牌を入力します===");
        System.out.println("1. 槓子・明刻・明順子の入力");
        System.out.println("2. 見せていない牌の入力");
        System.out.println("3. 和了牌の入力");
        System.out.println("の順で進みます");
    }

    @Override
    public void input() {
        boolean loop = false;
        while (!loop) {
            List<Mentsu> mentsuList = mentsuListInputSupport();
            Tile finalTile = tileInputSupport("和了牌を入力してください", false);
            int[] otherTiles = otherTileInputSupport(mentsuList.size());
            try {
                hands = new Hands(otherTiles, finalTile, mentsuList);
                // TODO: 全部表示して確認させる
                loop = boolInputSupport("本当にこれでいいですか？ 1:OK 0:やっぱりもう一回入力する");
            } catch (MahjongTileOverFlowException | IllegalMentsuSizeException e) {
                System.out.println("手牌としてありえないので、もう一度入力して下さい");
            }
        }
    }

    private List<Mentsu> mentsuListInputSupport() {
        List<Mentsu> menstuList = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            Mentsu mentsu = menstuInputSupport();
            if (mentsu == null) {
                break;
            }
            menstuList.add(mentsu);
        }

        return menstuList;
    }

    private Mentsu menstuInputSupport() {
        switch (inputSupport("面子の種類を選んで下さい 1:明順子 2:明刻子 3:明槓子 4:暗槓子 0:面子の入力を終了", 0, 4)) {
            case 1:
                return shuntsuInputSupport();
            case 2:
                return kotsuInputSupport();
            case 3:
                return kantsuInputSupport(true);
            case 4:
                return kantsuInputSupport(false);
        }
        return null;
    }

    private Kantsu kantsuInputSupport(boolean isOpen) {
        return new Kantsu(isOpen, tileInputSupport("槓子を構成する牌を入力してください", false));
    }

    private Kotsu kotsuInputSupport() {
        return new Kotsu(true, tileInputSupport("刻子を構成する牌を入力してください", false));
    }

    private Shuntsu shuntsuInputSupport() {
        String msg = "順子の2番目の牌を入力して下さい";

        while (true) {
            Tile tile = tileInputSupport(msg, false);
            try {
                return new Shuntsu(true, tile);
            } catch (IllegalShuntsuIdentifierException e) {
                System.out.println("順子の2番目に入り得ない牌です");
            }
        }
    }

    private int[] otherTileInputSupport(int menstuSize) {
        int[] tiles = new int[34];
        System.out.println("和了牌も含めて公開していない牌をひとつずつ入力して下さい");
        for (int i = 0; i < (14 - menstuSize * 3); i++) {

            tiles[tileInputSupport((i + 1) + "個目です", false).getCode()]++;
        }

        return tiles;
    }
}
