package com.eve.bookmarks.entitys.vo;

import com.eve.bookmarks.entitys.po.BookMark;

/**
 * 命令
 */

public class BkmkCommandVo {
    private String path;
    private BookMark bookMark;
    private int command;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public BookMark getBookMark() {
        return bookMark;
    }

    public void setBookMark(BookMark bookMark) {
        this.bookMark = bookMark;
    }


    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }
}
