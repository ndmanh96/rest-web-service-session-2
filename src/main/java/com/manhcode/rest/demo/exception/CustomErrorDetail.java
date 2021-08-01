package com.manhcode.rest.demo.exception;

import java.util.Date;

public class CustomErrorDetail {
    private Date timestamp;
    private String message;
    private String desc;

    public CustomErrorDetail() {

    }

    public CustomErrorDetail(Date timestamp, String message, String desc) {
        this.timestamp = timestamp;
        this.message = message;
        this.desc = desc;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDesc() {
        return desc;
    }
}
