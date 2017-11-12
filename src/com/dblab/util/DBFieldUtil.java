package com.dblab.util;

public class DBFieldUtil {
    public static String join(String start, String concat, String end, String ... items) {
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        for (int i = 0; i < items.length - 1; i++) {
            sb.append(items[i]);
            sb.append(concat);
        }
        sb.append(items[items.length - 1]);
        sb.append(end);
        return sb.toString();
    }
}
