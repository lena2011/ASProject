package com.lena.asp.utils;

import java.util.Locale;

/**
 * @auther lilingfei
 * @date 2018/7/14
 */

public class StringUtils {
    /**
     * 数字格式化
     * @param d
     * @return
     */
    public static String formatD(int d) {
        return String.format(Locale.CHINA, "%d", d);
    }
}
