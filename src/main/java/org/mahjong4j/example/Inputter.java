package org.mahjong4j.example;

import org.mahjong4j.tile.Tile;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author yu1ro
 */
public abstract class Inputter {
    protected final BufferedReader input;

    public Inputter(BufferedReader input) {
        this.input = input;
    }

    public abstract void printHeaderComment();

    public abstract void input();

    protected Tile tileInputSupport(String msg, boolean canNull) {
        System.out.println(msg);
        int typeNum;
        if (canNull) {
            typeNum = inputSupport("種類を入力してください\n0: 終了 1: 萬子 2: 筒子 3: 索子 4: 風牌 5: 三元牌", 0, 5);
        } else {
            typeNum = inputSupport("種類を入力してください\n1: 萬子 2: 筒子 3: 索子 4: 風牌 5: 三元牌", 1, 5);
        }
        switch (typeNum) {
            case 1:
                return manzuInputSupport();
            case 2:
                return pinzuInputSupport();
            case 3:
                return sohzuInputSupport();
            case 4:
                return fonpaiInputSupport("");
            case 5:
                return sangenInputSupport();
        }
        return null;
    }

    protected Tile sangenInputSupport() {
        final String msg = "1:白 2:発 3:中";
        switch (inputSupport(msg, 1, 3)) {
            case 1:
                return Tile.HAK;
            case 2:
                return Tile.HAT;
            case 3:
                return Tile.CHN;
        }
        throw new UnsupportedOperationException("想定外の例外");
    }

    protected Tile manzuInputSupport() {
        final String msg = "萬子の番号を教えて下さい";
        switch (inputSupport(msg, 1, 9)) {
            case 1:
                return Tile.M1;
            case 2:
                return Tile.M2;
            case 3:
                return Tile.M3;
            case 4:
                return Tile.M4;
            case 5:
                return Tile.M5;
            case 6:
                return Tile.M6;
            case 7:
                return Tile.M7;
            case 8:
                return Tile.M8;
            case 9:
                return Tile.M9;
        }
        throw new UnsupportedOperationException("想定外の例外");
    }

    protected Tile pinzuInputSupport() {
        final String msg = "筒子の番号を教えて下さい";
        switch (inputSupport(msg, 1, 9)) {
            case 1:
                return Tile.P1;
            case 2:
                return Tile.P2;
            case 3:
                return Tile.P3;
            case 4:
                return Tile.P4;
            case 5:
                return Tile.P5;
            case 6:
                return Tile.P6;
            case 7:
                return Tile.P7;
            case 8:
                return Tile.P8;
            case 9:
                return Tile.P9;
        }
        throw new UnsupportedOperationException("想定外の例外");
    }

    protected Tile sohzuInputSupport() {
        final String msg = "索子の番号を教えて下さい";
        switch (inputSupport(msg, 1, 9)) {
            case 1:
                return Tile.S1;
            case 2:
                return Tile.S2;
            case 3:
                return Tile.S3;
            case 4:
                return Tile.S4;
            case 5:
                return Tile.S5;
            case 6:
                return Tile.S6;
            case 7:
                return Tile.S7;
            case 8:
                return Tile.S8;
            case 9:
                return Tile.S9;
        }
        throw new UnsupportedOperationException("想定外の例外");
    }

    /**
     * allow only 1 or 0
     *
     * @param msg
     * @return
     */
    protected boolean boolInputSupport(String msg) {
        return inputSupport(msg, 0, 1) == 1;
    }

    protected Tile fonpaiInputSupport(String msg) {
        final String commonMsg = "1:東 2:南 3:西 4:北";
        msg = msg + "\n" + commonMsg;
        int fonNum = inputSupport(msg, 1, 4);
        switch (fonNum) {
            case 1:
                return Tile.TON;
            case 2:
                return Tile.NAN;
            case 3:
                return Tile.SHA;
            case 4:
                return Tile.PEI;
        }
        throw new UnsupportedOperationException("想定外の例外");
    }

    /**
     * 標準入力からの入力を受け付けます
     * 範囲内の数字を入力するまでメソッドを抜けることができません
     *
     * @param msg      Input message
     * @param rangeMin
     * @param rangeMax
     * @return
     */
    protected int inputSupport(String msg, int rangeMin, int rangeMax) {
        if (rangeMax < rangeMin) throw new IllegalArgumentException(rangeMin + " must be less than " + rangeMax);
        int output = 0;
        boolean isCorrect = false;
        while (!isCorrect) {
            System.out.println(msg);
            try {
                System.out.printf("> ");
                String str = input.readLine();
                output = Integer.parseInt(str);
                if (rangeMin <= output && rangeMax >= output) {
                    isCorrect = true;
                } else {
                    System.out.println(rangeMin + "から" + rangeMax + "の範囲で入力して下さい");
                    isCorrect = false;
                }
            } catch (IOException e) {
                isCorrect = false;
            } catch (NumberFormatException e) {
                System.out.println("数字を入力して下さい");
                isCorrect = false;
            }
        }

        return output;
    }
}
