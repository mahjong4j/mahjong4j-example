package org.mahjong4j.example.output;

import org.mahjong4j.Player;
import org.mahjong4j.yaku.normals.NormalYaku;
import org.mahjong4j.yaku.yakuman.Yakuman;

import java.util.List;

/**
 * @author yu1ro
 */
public class YakuOutputter implements Outputter {
    private List<Yakuman> yakumanList;
    private List<NormalYaku> yakuList;

    public YakuOutputter(Player player) {
        yakumanList = player.getYakumanList();
        yakuList = player.getNormalYakuList();
    }

    @Override
    public void print() {
        if (yakumanList.size() > 0) {
            System.out.println("！！！！！！！！役満！！！！！！！！");
            yakumanList.forEach(yakuman -> System.out.println(yakuman.getJapanese()));
        } else if (yakuList.size() > 0) {
            yakuList.forEach(yaku -> System.out.println(yaku.getJapanese()));
        } else {
            System.out.println("役ナシ。。。");
        }

    }
}
