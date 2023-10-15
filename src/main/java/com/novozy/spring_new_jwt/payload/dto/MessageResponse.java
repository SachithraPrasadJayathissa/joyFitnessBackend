package com.novozy.spring_new_jwt.payload.dto;

public class MessageResponse {
    private long status_code;
    private String message_status;
    private String message;
    private Object data;
    public MessageResponse() {
    }

    public MessageResponse(long status_code, String message, Object data) {
        this.status_code = status_code;
        this.message = message;
        this.data = data;
    }

    public MessageResponse(long status_code, String message) {
        this.status_code = status_code;
        this.message = message;
    }

    public MessageResponse(Object data) {
        this.data = data;
    }

    public MessageResponse(long status_code, String message_status, String message) {
        this.status_code = status_code;
        this.message_status = message_status;
        this.message = message;
    }
    public MessageResponse(long status_code, String message_status, String message, Object data) {
        this.status_code = status_code;
        this.message_status = message_status;
        this.message = message;
        this.data = data;
    }

    public MessageResponse(String message) {
        this.message = message;
    }

    public long getStatus_code() {
        return status_code;
    }

    public void setStatus_code(long status_code) {
        this.status_code = status_code;
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

    public String getMessage_status() {
        return message_status;
    }

    public void setMessage_status(String message_status) {
        this.message_status = message_status;
    }


}
