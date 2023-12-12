package nro.models.boss;

import nro.consts.ConstEvent;
import nro.consts.ConstMap;
import nro.models.boss.bill.*;
import nro.models.boss.bosstuonglai.*;
import nro.models.boss.broly.*;
import nro.models.boss.cell.*;
import nro.models.boss.chill.*;
import nro.models.boss.cold.*;
import nro.models.boss.event.HoaHong;
import nro.models.boss.event.Qilin;
import nro.models.boss.event.SantaClaus;
import nro.models.boss.fide.*;
import nro.models.boss.mabu_war.*;
import nro.models.boss.nappa.*;
import nro.models.boss.robotsatthu.*;
import nro.models.boss.tieudoisatthu.*;
import nro.models.boss.NguHanhSon.*;
import nro.models.boss.NgucTu.*;
import nro.models.boss.traidat.*;
import nro.models.map.Map;
import nro.models.map.Zone;
import nro.models.map.mabu.MabuWar;
import nro.server.Manager;
import nro.services.MapService;
import org.apache.log4j.Logger;

/**
 * @author 💖 Trần Lại 💖
 * @copyright 💖 NROLOVE 💖
 */
public class BossFactory {

    //id boss
    public static final byte BROLY = -1;
    public static final byte SUPER_BROLY = -2;
    public static final byte TRUNG_UY_TRANG = -3;
    public static final byte TRUNG_UY_XANH_LO = -4;
    public static final byte TRUNG_UD_THEP = -5;
    public static final byte NINJA_AO_TIM = -6;
    public static final byte NINJA_AO_TIM_FAKE_1 = -7;
    public static final byte NINJA_AO_TIM_FAKE_2 = -8;
    public static final byte NINJA_AO_TIM_FAKE_3 = -9;
    public static final byte NINJA_AO_TIM_FAKE_4 = -10;
    public static final byte NINJA_AO_TIM_FAKE_5 = -11;
    public static final byte NINJA_AO_TIM_FAKE_6 = -12;
    public static final byte ROBOT_VE_SI_1 = -13;
    public static final byte ROBOT_VE_SI_2 = -14;
    public static final byte ROBOT_VE_SI_3 = -15;
    public static final byte ROBOT_VE_SI_4 = -16;
    public static final byte XEN_BO_HUNG_1 = -17;
    public static final byte XEN_BO_HUNG_2 = -18;
    public static final byte XEN_BO_HUNG_HOAN_THIEN = -19;
    public static final byte XEN_BO_HUNG = -20;
    public static final byte XEN_CON = -21;
    public static final byte SIEU_BO_HUNG = -22;
    public static final byte KUKU = -23;
    public static final byte MAP_DAU_DINH = -24;
    public static final byte RAMBO = -25;
    public static final byte COOLER = -26;
    public static final byte COOLER2 = -27;
    public static final byte SO4 = -28;
    public static final byte SO3 = -29;
    public static final byte SO2 = -30;
    public static final byte SO1 = -31;
    public static final byte TIEU_DOI_TRUONG = -32;
    public static final byte FIDE_DAI_CA_1 = -33;
    public static final byte FIDE_DAI_CA_2 = -34;
    public static final byte FIDE_DAI_CA_3 = -35;
    public static final byte ANDROID_19 = -36;
    public static final byte ANDROID_20 = -37;
    public static final byte ANDROID_13 = -38;
    public static final byte ANDROID_14 = -39;
    public static final byte ANDROID_15 = -40;
    public static final byte PIC = -41;
    public static final byte POC = -42;
    public static final byte KINGKONG = -43;
    public static final byte SUPER_BROLY_RED = -44;
    public static final byte LUFFY = -45;
    public static final byte ZORO = -46;
    public static final byte SANJI = -47;
    public static final byte USOPP = -48;
    public static final byte FRANKY = -49;
    public static final byte BROOK = -50;
    public static final byte NAMI = -51;
    public static final byte CHOPPER = -52;
    public static final byte ROBIN = -53;
    public static final byte WHIS = -54;
    public static final byte BILL = -55;
    public static final byte CHILL = -56;
    public static final byte CHILL2 = -57;
    public static final byte BULMA = -58;
    public static final byte POCTHO = -59;
    public static final byte CHICHITHO = -60;
    public static final byte BLACKGOKU = -61;
    public static final byte SUPERBLACKGOKU = -62;
    public static final byte SANTA_CLAUS = -63;
    public static final byte MABU_MAP = -64;
    public static final byte SUPER_BU = -65;
    public static final byte BU_TENK = -66;
    public static final byte DRABULA_TANG1 = -67;
    public static final byte BUIBUI_TANG2 = -68;
    public static final byte BUIBUI_TANG3 = -69;
    public static final byte YACON_TANG4 = -70;
    public static final byte DRABULA_TANG5 = -71;
    public static final byte GOKU_TANG5 = -72;
    public static final byte CADIC_TANG5 = -73;
    public static final byte DRABULA_TANG6 = -74;
    public static final byte XEN_MAX = -75;
    public static final byte HOA_HONG = -76;
    public static final byte SOI_HEC_QUYN = -77;
    public static final byte O_DO = -78;
    public static final byte XINBATO = -79;
    public static final byte CHA_PA = -80;
    public static final byte PON_PUT = -81;
    public static final byte CHAN_XU = -82;
    public static final byte TAU_PAY_PAY = -83;
    public static final byte YAMCHA = -84;
    public static final byte JACKY_CHUN = -85;
    public static final byte THIEN_XIN_HANG = -86;
    public static final byte LIU_LIU = -87;
    public static final byte THIEN_XIN_HANG_CLONE = -88;
    public static final byte THIEN_XIN_HANG_CLONE1 = -89;
    public static final byte THIEN_XIN_HANG_CLONE2 = -90;
    public static final byte THIEN_XIN_HANG_CLONE3 = -91;
    public static final byte QILIN = -92;
    public static final byte NGO_KHONG = -93;
    public static final byte BAT_GIOI = -94;
    public static final byte FIDEGOLD = -95;
    public static final byte CUMBER = -96;
    public static final byte CUMBER2 = -97;


    private static final Logger logger = Logger.getLogger(BossFactory.class);

    public static final int[] MAP_APPEARED_QILIN = {ConstMap.VACH_NUI_ARU_42, ConstMap.VACH_NUI_MOORI_43, ConstMap.VACH_NUI_KAKAROT,
            ConstMap.LANG_ARU, ConstMap.LANG_MORI, ConstMap.LANG_KAKAROT, ConstMap.DOI_HOA_CUC, ConstMap.DOI_NAM_TIM, ConstMap.DOI_HOANG,
            ConstMap.TRAM_TAU_VU_TRU, ConstMap.TRAM_TAU_VU_TRU_25, ConstMap.TRAM_TAU_VU_TRU_26, ConstMap.LANG_PLANT, ConstMap.RUNG_NGUYEN_SINH,
            ConstMap.RUNG_CO, ConstMap.RUNG_THONG_XAYDA, ConstMap.RUNG_DA, ConstMap.THUNG_LUNG_DEN, ConstMap.BO_VUC_DEN, ConstMap.THANH_PHO_VEGETA,
            ConstMap.THUNG_LUNG_TRE, ConstMap.RUNG_NAM, ConstMap.RUNG_BAMBOO, ConstMap.RUNG_XUONG, ConstMap.RUNG_DUONG_XI, ConstMap.NAM_KAME,
            ConstMap.DAO_BULONG, ConstMap.DONG_KARIN, ConstMap.THI_TRAN_MOORI, ConstMap.THUNG_LUNG_MAIMA, ConstMap.NUI_HOA_TIM, ConstMap.NUI_HOA_VANG,
            ConstMap.NAM_GURU, ConstMap.DONG_NAM_GURU, ConstMap.THUNG_LUNG_NAMEC
    };

    private BossFactory() {

    }

    public static void initBoss() {
        new Thread(() -> {
            try {


                createBoss(NGO_KHONG);
                createBoss(CUMBER);


                createBoss(BULMA);
                createBoss(CHICHITHO);
                createBoss(POCTHO);
                createBoss(BLACKGOKU);
                createBoss(CHILL);
                createBoss(WHIS);
                createBoss(COOLER);
                createBoss(XEN_BO_HUNG);
                createBoss(KUKU);
                createBoss(MAP_DAU_DINH);
                createBoss(RAMBO);
                createBoss(TIEU_DOI_TRUONG);
                createBoss(FIDE_DAI_CA_1);
                createBoss(ANDROID_20);
                createBoss(KINGKONG);
                createBoss(XEN_BO_HUNG_1);
                createBoss(XEN_MAX);
                createBoss(SANTA_CLAUS);
                for (int i = 0; i < 5; i++) {
                    createBoss(SUPER_BROLY);
                }
                for (Map map : Manager.MAPS) {
                    if (map != null && !map.zones.isEmpty()) {
                        if (!map.isMapOffline && map.type == ConstMap.MAP_NORMAL
                                && map.tileId > 0 && !MapService.gI().isMapVS(map.mapId)) {
                            if (map.mapWidth > 50 && map.mapHeight > 50) {
                                if (Manager.EVENT_SEVER == ConstEvent.SU_KIEN_20_11) {
                                    new HoaHong(map.mapId);
                                }
                                if (Manager.EVENT_SEVER == ConstEvent.SU_KIEN_NOEL) {
                                    new SantaClaus(map.mapId);
                                }
                            }
                        }
                    }
                }
                if (Manager.EVENT_SEVER == ConstEvent.SU_KIEN_TET) {
                    for (int mapID : MAP_APPEARED_QILIN) {
                        new Qilin(mapID);
                    }
                }
            } catch (Exception e) {
                logger.error("Err initboss", e);
            }
        }).start();
    }

    public static void initBossMabuWar() {
        new Thread(() -> {
            for (short mapid : BossData.DRABULA_TANG1.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Drabula_Tang1(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.DRABULA_TANG6.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Drabula_Tang6(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.GOKU_TANG5.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Goku_Tang5(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.CALICH_TANG5.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Calich_Tang5(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.BUIBUI_TANG2.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new BuiBui_Tang2(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.BUIBUI_TANG3.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new BuiBui_Tang3(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.YACON_TANG4.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Yacon_Tang4(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
        }).start();
    }

    public static Boss createBoss(byte bossId) {
        Boss boss = null;
        switch (bossId) {
            case BROLY:
                boss = new Broly();
                break;
            case SUPER_BROLY:
                boss = new SuperBroly();
                break;
            case XEN_BO_HUNG_1:
                boss = new XenBoHung1();
                break;
            case XEN_BO_HUNG_2:
                boss = new XenBoHung2();
                break;
            case XEN_BO_HUNG_HOAN_THIEN:
                boss = new XenBoHungHoanThien();
                break;
            case XEN_BO_HUNG:
                boss = new XenBoHung();
                break;
            case XEN_CON:
                boss = new XenCon();
                break;
            case SIEU_BO_HUNG:
                boss = new SieuBoHung();
                break;
            case KUKU:
                boss = new Kuku();
                break;
            case MAP_DAU_DINH:
                boss = new MapDauDinh();
                break;
            case RAMBO:
                boss = new Rambo();
                break;
            case COOLER:
                boss = new Cooler();
                break;
            case COOLER2:
                boss = new Cooler2();
                break;
            case SO4:
                boss = new So4();
                break;
            case SO3:
                boss = new So3();
                break;
            case SO2:
                boss = new So2();
                break;
            case SO1:
                boss = new So1();
                break;
            case TIEU_DOI_TRUONG:
                boss = new TieuDoiTruong();
                break;
            case FIDE_DAI_CA_1:
                boss = new FideDaiCa1();
                break;
            case FIDE_DAI_CA_2:
                boss = new FideDaiCa2();
                break;
            case FIDE_DAI_CA_3:
                boss = new FideDaiCa3();
                break;
            case ANDROID_19:
                boss = new Android19();
                break;
            case ANDROID_20:
                boss = new Android20();
                break;
            case SUPER_BROLY_RED:
                boss = new SuperBrolyRed();
                break;
            case POC:
                boss = new Poc();
                break;
            case PIC:
                boss = new Pic();
                break;
            case KINGKONG:
                boss = new KingKong();
                break;
            case WHIS:
                boss = new Whis();
                break;
            case BILL:
                boss = new Bill();
                break;
            case CHILL:
                boss = new Chill();
                break;
            case CHILL2:
                boss = new Chill2();
                break;
            case BULMA:
                boss = new BULMA();
                break;
            case POCTHO:
                boss = new POCTHO();
                break;
            case CHICHITHO:
                boss = new CHICHITHO();
                break;
//             case BROLYDEN:
//                boss = new Brolyden();
//                break;
//             case BROLYXANH:
//                boss = new Brolyxanh();
//                break;
//             case BROLYVANG:
//                boss = new Brolyvang();
//                break;
            case BLACKGOKU:
                boss = new Blackgoku();
                break;
            case SUPERBLACKGOKU:
                boss = new Superblackgoku();
                break;
            case MABU_MAP:
                boss = new Mabu_Tang6();
                break;
            case XEN_MAX:
                boss = new XenMax();
                break;
            case NGO_KHONG:
                boss = new NgoKhong();
                break;
            case BAT_GIOI:
                boss = new BatGioi();
                break;
            case FIDEGOLD:
                boss = new FideGold();
                break;
            case CUMBER:
                boss = new Cumber();
                break;
            case CUMBER2:
                boss = new SuperCumber();
                break;
        }
        return boss;
    }

}
