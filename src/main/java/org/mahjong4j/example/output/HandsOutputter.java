package org.mahjong4j.example.output;

import org.mahjong4j.hands.Hands;
import org.mahjong4j.tile.Tile;

/**
 * @author yu1ro
 */
public class HandsOutputter implements Outputter {
    private Hands hands;

    public HandsOutputter(Hands hands) {
        this.hands = hands;
    }

    @Override
    public void print() {
        int[] handsComp = hands.getHandsComp();
        for (int i = 0; i < handsComp.length; i++) {
            if (handsComp[i] > 0) {
                System.out.printf(String.valueOf(Tile.valueOf(i)) + " ");
            }
        }
        System.out.println();
    }
}
