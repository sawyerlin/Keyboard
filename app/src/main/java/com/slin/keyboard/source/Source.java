package com.slin.keyboard.source;

import java.util.Hashtable;

public final class Source {
    private static Hashtable<String, String> source;

    static {
        source = new Hashtable<>();
        source.put("1", "a");
        source.put("11", "b");
        source.put("111", "c");
        source.put("12", "d");
        source.put("121", "e");
        source.put("1311", "f");
        source.put("5", "g");
        source.put("7", "h");
        source.put("9", "i");
        source.put("91", "j");
        source.put("92", "k");
        source.put("6", "l");
        source.put("61", "m");
        source.put("62", "n");
        source.put("63", "o");
        source.put("64", "p");
        source.put("65", "q");
        source.put("66", "r");
        source.put("67", "s");
        source.put("68", "t");
        source.put("69", "u");
        source.put("70", "v");
        source.put("71", "w");
        source.put("72", "x");
        source.put("73", "y");
        source.put("74", "z");
    }

    public static String findValue(String code) {
        String result = source.get(code);
        if (result == null) {
            result = code;
        }
        return result;
    }

}
