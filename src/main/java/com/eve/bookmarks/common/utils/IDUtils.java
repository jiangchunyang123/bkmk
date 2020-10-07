package com.eve.bookmarks.common.utils;

import java.util.UUID;

public class IDUtils {
    public static String uuid(){
        return UUID.randomUUID().toString();
    }
}
