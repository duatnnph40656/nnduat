package nro.models.map.war;

import nro.models.item.Item;
import nro.models.map.ItemMap;
import nro.models.map.Map;
import nro.models.player.Player;
import nro.services.PlayerService;
import nro.services.Service;
import nro.services.func.ChangeMapService;
import nro.utils.Log;
import nro.utils.TimeUtil;
import nro.utils.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 💖 Trần Lại 💖
 * @copyright 💖 GirlkuN 💖
 *
 */
public class BlackBallWar {

    private static final int TIME_CAN_PICK_BLACK_BALL_AFTER_DROP = 5000;

    public static final byte X3 = 3;
    public static final byte X5 = 5;
    public static final byte X7 = 7;

    public static final int COST_X3 = 100000000;
    public static final int COST_X5 = 300000000;
    public static final int COST_X7 = 500000000;

    public static final byte HOUR_OPEN = 20;
    public static final byte MIN_OPEN = 0;
    public static final byte SECOND_OPEN = 0;

    public static final byte HOUR_CAN_PICK_DB = 20;
    public static final byte MIN_CAN_PICK_DB = 30;
    public static final byte SECOND_CAN_PICK_DB = 0;

    public static final byte HOUR_CLOSE = 21;
    public static final byte MIN_CLOSE = 0;
    public static final byte SECOND_CLOSE = 0;

//    public static final byte HOUR_OPEN = 1;
//    public static final byte MIN_OPEN = 0;
//    public static final byte SECOND_OPEN = 0;
//
//    public static final byte HOUR_CAN_PICK_DB = 20;
//    public static final byte MIN_CAN_PICK_DB = 30;
//    public static final byte SECOND_CAN_PICK_DB = 0;
//
//    public static final byte HOUR_CLOSE = 24;
//    public static final byte MIN_CLOSE = 0;
//    public static final byte SECOND_CLOSE = 0;
    public static final byte ZONES = 7;
    private static final int TIME_WIN = 300000;

    private static BlackBallWar i;

    public static long TIME_OPEN;
    private static long TIME_CAN_PICK_DB;
    public static long TIME_CLOSE;

    private int day = -1;

    private BlackBallWar() {
        this.maps = new ArrayList<>();
    }

    public static BlackBallWar gI() {
        if (i == null) {
            i = new BlackBallWar();
        }
        i.setTime();
        return i;
    }

    public void setTime() {
        if (i.day == -1 || i.day != TimeUtil.getCurrDay()) {
            i.day = TimeUtil.getCurrDay();
            try {
                this.TIME_OPEN = TimeUtil.getTime(TimeUtil.getTimeNow("dd/MM/yyyy") + " " + HOUR_OPEN + ":" + MIN_OPEN + ":" + SECOND_OPEN, "dd/MM/yyyy HH:mm:ss");
                this.TIME_CAN_PICK_DB = TimeUtil.getTime(TimeUtil.getTimeNow("dd/MM/yyyy") + " " + HOUR_CAN_PICK_DB + ":" + MIN_CAN_PICK_DB + ":" + SECOND_CAN_PICK_DB, "dd/MM/yyyy HH:mm:ss");
                this.TIME_CLOSE = TimeUtil.getTime(TimeUtil.getTimeNow("dd/MM/yyyy") + " " + HOUR_CLOSE + ":" + MIN_CLOSE + ":" + SECOND_CLOSE, "dd/MM/yyyy HH:mm:ss");
            } catch (Exception e) {
            }
        }
    }

    private List<Map> maps;

    public void addMap(Map map) {
        this.maps.add(map);
    }

    public void dropBlackBall(Player player) {
        if (player.isHoldBlackBall) {
            ItemMap itemMap = new ItemMap(player.zone,
                    player.tempIdBlackBallHold, 1, player.location.x,
                    player.zone.map.yPhysicInTop(player.location.x, player.location.y - 24),
                    -1);
            Service.getInstance().dropItemMap(itemMap.zone, itemMap);
            player.isHoldBlackBall = false;
            player.tempIdBlackBallHold = -1;
            player.zone.lastTimeDropBlackBall = System.currentTimeMillis();
            Service.getInstance().sendFlagBag(player);

            if (player.clan != null) {
                List<Player> players = player.zone.getPlayers();
                synchronized (players) {
                    for (Player pl : players) {
                        if (pl.clan != null && player.clan.equals(pl.clan)) {
                            Service.getInstance().changeFlag(pl, Util.nextInt(1, 7));
                        }
                    }
                }
            } else {
                Service.getInstance().changeFlag(player, Util.nextInt(1, 7));
            }
        }
    }

    public void update(Player player) {
        if (player.isHoldBlackBall) {
            if (Util.canDoWithTime(player.lastTimeHoldBlackBall, TIME_WIN)) {
                win(player);
                return;
            } else {
                if (Util.canDoWithTime(player.lastTimeNotifyTimeHoldBlackBall, 10000)) {
                    Service.getInstance().sendThongBao(player, "Cố gắng giữ ngọc rồng trong "
                            + TimeUtil.getSecondLeft(player.lastTimeHoldBlackBall, TIME_WIN / 1000)
                            + " giây nữa, đem chiến thắng về cho bang hội!");
                    player.lastTimeNotifyTimeHoldBlackBall = System.currentTimeMillis();
                }
            }
        }
        try {
            if (player.zone.map.mapId >= 85 && player.zone.map.mapId <= 91) {
                long now = System.currentTimeMillis();
                if (!(now > TIME_OPEN && now < TIME_CLOSE)) {
                    if (player.isHoldBlackBall) {
                        win(player);
                    } else {
                        kickOutOfMap(player);
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    private void win(Player player) {
        player.zone.finishBlackBallWar = true;
        int star = player.tempIdBlackBallHold - 371;
        if (player.clan != null) {
            try {
                List<Player> players = player.clan.membersInGame;
                for (Player pl : players) {
                    if (pl != null) {
                        pl.rewardBlackBall.reward((byte) star);
                        Service.getInstance().sendThongBao(pl, "Chúc mừng bang hội của bạn đã "
                                + "dành chiến thắng ngọc rồng sao đen " + star + " sao");
                    }
                }

            } catch (Exception e) {
                Log.error(BlackBallWar.class, e,
                        "Lỗi ban thưởng ngọc rồng đen "
                        + star + " sao cho clan " + player.clan.id);
            }
        } else {
            player.rewardBlackBall.reward((byte) star);
            Service.getInstance().sendThongBao(player, "Chúc mừng bang hội của bạn đã "
                    + "dành chiến thắng ngọc rồng sao đen " + star + " sao");
        }

        List<Player> playersMap = player.zone.getPlayers();
        synchronized (playersMap) {
            for (Player pl : playersMap) {
                kickOutOfMap(pl);
            }
        }
    }

    private void kickOutOfMap(Player player) {
        if (player.cFlag == 8) {
            Service.getInstance().changeFlag(player, Util.nextInt(1, 7));
        }
        Service.getInstance().sendThongBao(player, "Trận đại chiến đã kết thúc, tàu vận chuyển sẽ đưa bạn về nhà");
        ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, -1, 250);
    }

    public void changeMap(Player player, byte index) {
        try {
            long now = System.currentTimeMillis();
            if (now > TIME_OPEN && now < TIME_CLOSE) {
                ChangeMapService.gI().changeMap(player,
                        player.mapBlackBall.get(index).map.mapId, -1, 50, 50);
            } else {
                Service.getInstance().sendThongBao(player, "Đại chiến ngọc rồng đen chưa mở");
                Service.getInstance().hideWaitDialog(player);
            }
        } catch (Exception ex) {
        }
    }

    public void joinMapBlackBallWar(Player player) {
        boolean changed = false;
        if (player.clan != null) {
            List<Player> players = player.zone.getPlayers();
            synchronized (players) {
                for (Player pl : players) {
                    if (pl.clan != null && !player.equals(pl) && player.clan.equals(pl.clan)) {
                        Service.getInstance().changeFlag(player, pl.cFlag);
                        changed = true;
                        break;
                    }
                }
            }
        }
        if (!changed) {
            Service.getInstance().changeFlag(player, Util.nextInt(1, 7));
        }
    }

    public boolean pickBlackBall(Player player, Item item) {
        try {
            if (System.currentTimeMillis() < this.TIME_CAN_PICK_DB) {
                Service.getInstance().sendThongBao(player, "Chưa thể nhặt ngọc rồng ngay lúc này, vui lòng đợi "
                        + TimeUtil.diffDate(new Date(this.TIME_CAN_PICK_DB),
                                new Date(System.currentTimeMillis()), TimeUtil.SECOND) + " giây nữa");
                return false;
            } else if (player.zone.finishBlackBallWar) {
                Service.getInstance().sendThongBao(player, "Đại chiến ngọc rồng sao đen "
                        + "đã kết thúc, vui lòng đợi đến ngày mai");
                return false;
            } else {
                if (Util.canDoWithTime(player.zone.lastTimeDropBlackBall, TIME_CAN_PICK_BLACK_BALL_AFTER_DROP)) {

                    player.isHoldBlackBall = true;
                    player.tempIdBlackBallHold = item.template.id;
                    player.lastTimeHoldBlackBall = System.currentTimeMillis();
                    Service.getInstance().sendFlagBag(player);
                    if (player.clan != null) {
                        List<Player> players = player.zone.getPlayers();
                        synchronized (players) {
                            for (Player pl : players) {
                                if (pl.clan != null && player.clan.equals(pl.clan)) {
                                    Service.getInstance().changeFlag(pl, 8);
                                }
                            }
                        }
                    } else {
                        Service.getInstance().changeFlag(player, 8);
                    }
                    return true;
                } else {
                    Service.getInstance().sendThongBao(player, "Không thể nhặt ngọc rồng đen ngay lúc này");
                    return false;
                }
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public void xHPKI(Player player, byte x) {
        int cost = 0;
        switch (x) {
            case X3:
                cost = COST_X3;
                break;
            case X5:
                cost = COST_X5;
                break;
            case X7:
                cost = COST_X7;
                break;
        }
        if (player.inventory.gold >= cost) {
            player.inventory.gold -= cost;
            Service.getInstance().sendMoney(player);
            player.effectSkin.lastTimeXHPKI = System.currentTimeMillis();
            player.effectSkin.xHPKI = x;
            player.nPoint.calPoint();
            player.nPoint.setHp((long) player.nPoint.hp * x);
            player.nPoint.setMp((long) player.nPoint.mp * x);
            PlayerService.gI().sendInfoHpMp(player);
            Service.getInstance().point(player);
        } else {
            Service.getInstance().sendThongBao(player, "Không đủ vàng để thực hiện, còn thiếu "
                    + Util.numberToMoney(cost - player.inventory.gold) + " vàng");
        }
    }
}
