package nro.models.boss.fide;

import nro.models.boss.*;
import nro.models.player.Player;
import nro.server.ServerNotify;
import nro.services.TaskService;
import nro.services.func.ChangeMapService;

/**
 *
 * @author 💖 Trần Lại 💖
 * @copyright 💖 GirlkuN 💖
 *
 */
public class FideDaiCa2 extends FutureBoss {

    public FideDaiCa2() {
        super(BossFactory.FIDE_DAI_CA_2, BossData.FIDE_DAI_CA_2);
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
        Boss fd3 = BossFactory.createBoss(BossFactory.FIDE_DAI_CA_3);
        fd3.zone = this.zone;
        fd3.location.x = this.location.x;
        fd3.location.y = this.location.y;
        super.leaveMap();
        BossManager.gI().removeBoss(this);
    }

    @Override
    public void joinMap() {
        if (this.zone != null) {
            ChangeMapService.gI().changeMap(this, zone, this.location.x, this.location.y);
            ServerNotify.gI().notify("Boss " + this.name + " vừa xuất hiện tại " + this.zone.map.mapName);
        }
    }
}
