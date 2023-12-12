package nro.models.boss.fide;

import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.FutureBoss;
import nro.models.player.Player;
import nro.services.TaskService;

/**
 *
 * @author 💖 Trần Lại 💖
 * @copyright 💖 GirlkuN 💖
 *
 */
public class FideDaiCa1 extends FutureBoss {

    public FideDaiCa1() {
        super(BossFactory.FIDE_DAI_CA_1, BossData.FIDE_DAI_CA_1);
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
        this.textTalkMidle = new String[]{"Xem bản lĩnh của ngươi như nào đã", "Các ngươi tới số mới gặp phải ta"};
        this.textTalkAfter = new String[]{"Ác quỷ biến hình, hêy aaa......."};
    }

    @Override
    public void leaveMap() {
        Boss fd2 = BossFactory.createBoss(BossFactory.FIDE_DAI_CA_2);
        fd2.zone = this.zone;
        fd2.location.x = this.location.x;
        fd2.location.y = this.location.y;
        super.leaveMap();
        this.setJustRestToFuture();
    }

}
