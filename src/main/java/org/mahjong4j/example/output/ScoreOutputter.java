package org.mahjong4j.example.output;

import org.mahjong4j.Player;

/**
 * @author yu1ro
 */
public class ScoreOutputter implements Outputter {

    private Player player;

    public ScoreOutputter(Player player) {
        this.player = player;
    }

    @Override
    public void print() {
        System.out.printf("%d符%d翻\n", player.getFu(), player.getHan());
        System.out.println(player.getScore().getRon() + "点");
    }
}
