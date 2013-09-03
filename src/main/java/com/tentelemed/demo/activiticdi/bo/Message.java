package com.tentelemed.demo.activiticdi.bo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 02/09/13
 * Time: 17:40
 */
public class Message implements Serializable {
    public final static String HELLO = "hello";
    public final static String GOODBYE = "bye";

    String message;
    String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
