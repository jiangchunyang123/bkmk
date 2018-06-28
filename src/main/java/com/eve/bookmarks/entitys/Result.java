package com.eve.bookmarks.entitys;

public class Result {
    private int state =1;
    private String message;
    private Object data ;

    public Result(int state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }
    static class Success {
        private final static Result instance= new Result(1,"success");
    }
    public static Result Success(){
        return Success.instance;
    }
    /**
     *
     * @param message 消息
     * @param data
     */
    public Result(String message, Object data) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
