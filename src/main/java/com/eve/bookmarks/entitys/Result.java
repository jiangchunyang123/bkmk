package com.eve.bookmarks.entitys;

public class Result {
    private int state =1;
    private String message;
    private String data ;

    public Result(int state, String message, String data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    /**
     *
     * @param message 消息
     * @param data
     */
    public Result(String message, String data) {
        this.message = message;
        this.data = data;
    }

    public Result(int state, String message) {

        this.state = state;
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
