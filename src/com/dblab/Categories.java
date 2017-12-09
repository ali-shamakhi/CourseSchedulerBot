package com.dblab;

import java.util.HashMap;

public class Categories {
    private static HashMap<String, Integer> _nameCodeMap = new HashMap<String, Integer>();
    private static HashMap<Integer, String> _codeNameMap = new HashMap<Integer, String>();

    static {
        _nameCodeMap.put("اصلی", 0);
        _codeNameMap.put(0, "اصلی");
        _nameCodeMap.put("پایه", 1);
        _codeNameMap.put(1, "پایه");
        _nameCodeMap.put("عمومی", 2);
        _codeNameMap.put(2, "عمومی");
        _nameCodeMap.put("تخصصی", 3);
        _codeNameMap.put(3, "تخصصی");
        _nameCodeMap.put("اختیاری", 4);
        _codeNameMap.put(4, "اختیاری");
    }

    public static int getCode(String categoryName) {
        return _nameCodeMap.get(categoryName);
    }

    public static String getName(int categoryCode) {
        return _codeNameMap.get(categoryCode);
    }

}
