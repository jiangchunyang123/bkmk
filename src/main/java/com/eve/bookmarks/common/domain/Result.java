package com.eve.bookmarks.common.domain;

public class Result {
    private int state =0;
    private String message;
    private Object data ;

    public Result(int state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }
    static class Success {
        private final static Result instance= new Result(0,"success");
    }
    public static Result Success(Object data){
        Result  result = Success.instance;
        result.setData(data);
        return result;
    }
    public static Result Success(){
        return Success(null);
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
