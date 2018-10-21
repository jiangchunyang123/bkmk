package com.eve.bookmarks.utils;

public class Constants {
    /**
     * mongodb中书签存储集合
     */
    public static String BOOK_MARK_MONGODB_NAME = "bookmark";

    public static String STATUS_SUCCESS = "1";
    public static String STATUS_FAILED = "-1";

    public static int SCHEDULE_CLYCLE_ONCE = 1;
    public static int SCHEDULE_CLYCLE_LOOP = 2;

    //时间统一标识
    public static int HOUR = 1;
    public static int DAY = 2;
    public static int MONTH = 3;
    public static int YEAR = 4;

    //有效标识
    public static int STATE_ENABLE = 1;
    public static int STATE_DISABLE = 0;

    //在线离线
    public static int ONLINE = 1;
    public static int OFFLINE = -1;

}
