package com.slin.keyboard.source;

import java.util.Hashtable;

public final class Source {
    private static Hashtable<SourceType, Hashtable<String, String>> source = new Hashtable<>();

    static {
        Hashtable <String, String> latinSource = new Hashtable<>();
        Hashtable <String, String> arabicSource = new Hashtable<>();
        source.put(SourceType.Latin, latinSource);
        source.put(SourceType.Arabic, arabicSource);
        latinSource.put("1", "a");
        latinSource.put("2", "b");
        latinSource.put("3", "c");
        latinSource.put("4", "d");
        latinSource.put("5", "e");
        latinSource.put("6", "f");
        latinSource.put("7", "g");
        latinSource.put("8", "h");
        latinSource.put("9", "i");
        latinSource.put("10", "j");
        latinSource.put("11", "k");
        latinSource.put("12", "l");
        latinSource.put("13", "m");
        latinSource.put("14", "n");
        latinSource.put("15", "o");
        latinSource.put("16", "p");
        latinSource.put("17", "q");
        latinSource.put("18", "r");
        latinSource.put("19", "s");
        latinSource.put("20", "t");
        latinSource.put("21", "u");
        latinSource.put("22", "v");
        latinSource.put("23", "w");
        latinSource.put("24", "x");
        latinSource.put("25", "y");
        latinSource.put("26", "z");

        arabicSource.put("1", "ا");
        arabicSource.put("2", "ح");
        arabicSource.put("3", "ع");
        arabicSource.put("4", "ر");
        arabicSource.put("5", "ى");
        arabicSource.put("6", "ف");
        arabicSource.put("7", "ل");
        arabicSource.put("8", "ص");
        arabicSource.put("9", "و");
        arabicSource.put("10", "ن");
        arabicSource.put("11", "س");
        arabicSource.put("12", "خ");
        arabicSource.put("13", "غ");
        arabicSource.put("14", "ز");
        arabicSource.put("15", "ئ");
        arabicSource.put("16", "ظ");
        arabicSource.put("17", "ب");
        arabicSource.put("18", "ض");
        arabicSource.put("19", "ؤ");
        arabicSource.put("20", "ت");
        arabicSource.put("21", "ش");
        arabicSource.put("22", "م");
        arabicSource.put("23", "ء");
        arabicSource.put("24", "[]");
        arabicSource.put("25", "ي");
        arabicSource.put("26", "ة");
        arabicSource.put("27", "ك");
        arabicSource.put("28", "لا");
        arabicSource.put("29", "ق");
        arabicSource.put("30", "ث");
    }

    public static String findValue(SourceType type, String code) {
        String result = source.get(type).get(code);
        if (result == null) {
            result = code;
        }
        return result;
    }

}
