package nro.models.boss.boss_ban_do_kho_bau;

import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.map.phoban.BanDoKhoBau;
import nro.services.func.ChangeMapService;
import nro.utils.Util;

/**
 *
 * @author 💖 Trần Lại 💖
 * @copyright 💖 GirlkuN 💖
 *
 */
public class Sanji extends BossBanDoKhoBau {

    public Sanji(BanDoKhoBau banDoKhoBau) {
        super(BossFactory.SANJI, BossData.SANJI, banDoKhoBau);
    }

    @Override
    public void initTalk() {
        this.textTalkMidle = new String[]{
            "Đừng có khơi mào một trận chiến nếu ngươi không kết thúc được nó!",
            "Là đàn ông, chúng ta phải sẵn sàng tha thứ cho lời nói dối của phụ nữ!",
            "Dù có chết, tôi cũng không đánh phụ nữ!",
            "Con dao là linh hồn của người đầu bếp, không phải là thứ để các ngươi tự do múa máy như thế!",
            "Miễn là còn điều gì đó cần được bảo vệ, tôi sẽ vẫn tiếp tục chiến đấu!"
        };
    }

    @Override
    public void joinMap() {
        try {
            this.zone = this.banDoKhoBau.getMapById(mapJoin[Util.nextInt(0, mapJoin.length - 1)]);
            ChangeMapService.gI().changeMap(this, this.zone, 115, 456);
        } catch (Exception e) {

        }
    }

    @Override
    public void leaveMap() {
        for (BossBanDoKhoBau boss : this.banDoKhoBau.bosses) {
            if (boss.id == BossFactory.ZORO) {
                boss.changeToAttack();
                break;
            }
        }
        super.leaveMap();
    }

}
