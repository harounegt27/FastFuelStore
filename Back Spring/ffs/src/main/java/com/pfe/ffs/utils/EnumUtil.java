package com.pfe.ffs.utils;

import com.pfe.ffs.enums.Paiment;

public class EnumUtil {
    public static Paiment getPaimentFromString(String paimentString) {
        if (paimentString == null) {
            return null;
        }
        return Paiment.valueOf(paimentString.toUpperCase().replace(" ", "_"));
    }
}
