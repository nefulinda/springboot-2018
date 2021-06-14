package com.nefu.myspringboot.utils;

import com.nefu.myspringboot.common.Hour;

public class LabUtils {
    public static int[][][] labList = new int[Hour.WEEK][Hour.DAY][Hour.SECTION];

    public static void initLab() {
        for (int i = 0; i < Hour.WEEK; i++) {
            for (int j = 0; j < Hour.DAY; j++) {
                for (int k = 0; k < Hour.SECTION; k++) {
                    labList[i][j][k] = 0;
                }
            }
        }
    }

}