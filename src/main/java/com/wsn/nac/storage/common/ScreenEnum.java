package com.wsn.nac.storage.common;

public enum ScreenEnum {
    SCREEN1(8, 16),
    SCREEN2(12, 19),
    SCREEN3(7, 14),
    OTHERS(0, 0);
    private static final ScreenEnum[] SCREENS = {SCREEN1, SCREEN2, SCREEN3, OTHERS};
    private final Integer rows;
    private final Integer cols;

    ScreenEnum(Integer rows, Integer cols) {
        this.rows = rows;
        this.cols = cols;
    }
    public int getRows(){
        return rows;
    }
    public int getCols(){
        return cols;
    }
    // 下标默认从0开始
    public static ScreenEnum select(int num){
        return SCREENS[num];
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
    public static int count(){
        return SCREENS.length;
    }
}
