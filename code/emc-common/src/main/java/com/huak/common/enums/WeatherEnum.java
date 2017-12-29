package com.huak.common.enums;

/**
 * Created by MR-BIN on 2017/7/11.
 */
public enum WeatherEnum {
    CLEAR(1,0),CLOUDY(2,1),SHADE(3,2),SHOWER(4,3),THUNDERSHOWER(5,4),THUNDERSHOWERHAIL(6,5),
    SLEET(7,6),SPRINKLE(8,7),MODERATERAIN(9,8),SOAKER(10,9),RAINSTORM(11,10),DOWNPOUR(12,11),
    RAINSTORMS(13,12),SNOWSHOWER(14,13),FLURRY(15,14),SNOW(16,15),HEAVYSNOW(17,16),BLIZZARD(18,17),
    FOG(19,18),ICERAIN(20,19),SANDSTORM(21,20),LIGHTRAINMODERATERAIN(22,21),MODERATERAINHEAVYRAIN(23,22),
    DOWNPOURRAINSTORM(24,23),RAINSTORMDOWNPOUR(25,24),DOWNPOURRAINSTORMS(26,25),FLURRYSNOW(27,26),SNOWHEAVYSNOW(28,27),
    HEAVYSNOWBLIZZARD(29,28),DUST(30,29),JANSA(31,30),HEAVYDUSTSTORM(32,31),HAZE(33,53);
    // 成员变量
    private int WEATHERID;
    private int WEATHERICON;
    // 构造方法
    private WeatherEnum(int weatherid, int weatheridIcon) {
        this.WEATHERID = weatherid;
        this.WEATHERICON = weatheridIcon;
    }
    // 普通方法
    public static String getName(int weatherid) {
        for (WeatherEnum c : WeatherEnum.values()) {
            if (c.getWEATHERID() == weatherid) {
                return c.WEATHERICON+"";
            }
        }
        return null;
    }

    public int getWEATHERID() {
        return WEATHERID;
    }

    public int getWEATHERICON() {
        return WEATHERICON;
    }
}
