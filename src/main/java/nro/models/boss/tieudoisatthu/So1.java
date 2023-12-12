package nro.models.boss.tieudoisatthu;

import nro.models.boss.*;
import nro.models.player.Player;
import nro.services.TaskService;

/**
 *
 * @author 💖 Trần Lại 💖
 * @copyright 💖 GirlkuN 💖
 *
 */
public class So1 extends FutureBoss {

    public So1() {
        super(BossFactory.SO1, BossData.SO1);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
        TaskService.gI().checkDoneTaskKillBoss(pl, this);
        generalRewards(pl);
    }

    @Override
    public void idle() {

    }

    @Override
    public void checkPlayerDie(Player pl) {

    }

    @Override
    public void initTalk() {
        this.textTalkMidle = new String[]{"Oải rồi hả?", "Ê cố lên nhóc",
            "Chán", "Đại ca Fide có nhầm không nhỉ"};

    }

    @Override
    public void leaveMap() {
        if (BossManager.gI().getBossById(BossFactory.SO2) == null) {
            BossManager.gI().getBossById(BossFactory.TIEU_DOI_TRUONG).changeToAttack();
        }
        super.leaveMap();
        BossManager.gI().removeBoss(this);
    }

}
