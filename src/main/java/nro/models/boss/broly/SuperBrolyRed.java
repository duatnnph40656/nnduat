package nro.models.boss.broly;

import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.player.Player;

/**
 *
 * @author 💖 Trần Lại 💖
 * @copyright 💖 GirlkuN 💖
 *
 */
public class SuperBrolyRed extends SuperBroly {

    public SuperBrolyRed() {
        super(BossFactory.SUPER_BROLY_RED, BossData.SUPER_BROLY_RED);
    }

    @Override
    public void rewards(Player pl) {
        this.dropItemReward(568, (int) pl.id);
        generalRewards(pl);
    }
}
