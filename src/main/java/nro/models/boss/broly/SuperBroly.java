package nro.models.boss.broly;

import nro.consts.ConstRatio;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.player.Player;
import nro.services.SkillService;
import nro.utils.Log;
import nro.utils.SkillUtil;
import nro.utils.Util;

/**
 *
 * @author 💖 Trần Lại 💖
 * @copyright 💖 GirlkuN 💖
 *
 */
public class SuperBroly extends Broly {

    public SuperBroly() {
        super(BossFactory.SUPER_BROLY, BossData.SUPER_BROLY);
        this.nPoint.defg = (short) (this.nPoint.hpg / 1000);
        if (this.nPoint.defg < 0) {
            this.nPoint.defg = (short) -this.nPoint.defg;
        }
    }

    public SuperBroly(byte id, BossData data) {
        super(id, data);
        this.nPoint.defg = (short) (this.nPoint.hpg / 1000);
        if (this.nPoint.defg < 0) {
            this.nPoint.defg = (short) -this.nPoint.defg;
        }
    }

    @Override
    public void attack() {
        try {
            if (!charge()) {
                Player pl = getPlayerAttack();
                if (pl != null) {
                    this.playerSkill.skillSelect = this.getSkillAttack();
                    if (Util.getDistance(this, pl) <= this.getRangeCanAttackWithSkillSelect()) {
                        if (Util.isTrue(15, ConstRatio.PER100) && SkillUtil.isUseSkillChuong(this)) {
                            goToXY(pl.location.x + (Util.getOne(-1, 1) * Util.nextInt(20, 80)),
                                    Util.nextInt(10) % 2 == 0 ? pl.location.y : pl.location.y - Util.nextInt(0, 50), false);
                        }
                        this.effectCharger();
                        try {
                            SkillService.gI().useSkill(this, pl, null);
                        } catch (Exception e) {
                            Log.error(SuperBroly.class, e);
                        }
                    } else {
                        goToPlayer(pl, false);
                    }
                    if (Util.isTrue(5, ConstRatio.PER100)) {
                        this.changeIdle();
                    }
                }
            }
        } catch (Exception ex) {
            Log.error(SuperBroly.class, ex);
        }
    }

    @Override
    public Player getPlayerAttack() throws Exception {
        if (countChangePlayerAttack < targetCountChangePlayerAttack
                && plAttack != null && plAttack.zone != null && plAttack.zone.equals(this.zone)
                && !plAttack.effectSkin.isVoHinh) {
            if (!plAttack.isDie()) {
                this.countChangePlayerAttack++;
                return plAttack;
            } else {
                plAttack = null;
            }
        } else {
            this.targetCountChangePlayerAttack = Util.nextInt(10, 20);
            this.countChangePlayerAttack = 0;
            plAttack = this.zone.getRandomPlayerInMap();
        }
        return plAttack;
    }

    @Override
    public void leaveMap() {
        super.leaveMap();
    }

    @Override
    public void die() {
        this.secondTimeRestToNextTimeAppear = 900; //15p
        super.die();
    }

    @Override
    public void rewards(Player pl) {

        this.dropItemReward(568, (int) pl.id);
        generalRewards(pl);
//        if (pl.pet == null) {
//            PetService.gI().createNormalPet(pl);
//        }
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

}
