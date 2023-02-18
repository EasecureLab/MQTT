package com.wsn.nac.storage.common;

public enum ScreenEnum {
    SCREENWelcome, // 迎宾屏
    SCREEN1, // 大屏1
    SCREEN2, // 大屏2
    OTHERS;  // 其他

    private static final ScreenEnum[] SCREENS = {SCREENWelcome,SCREEN1, SCREEN2, OTHERS};

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

