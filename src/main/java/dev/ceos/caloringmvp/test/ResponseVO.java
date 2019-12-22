package dev.ceos.caloringmvp.test;


public class ResponseVO {

    public ResponseVO() {}

    public ResponseVO(String m) {
        message = m;
    }
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
