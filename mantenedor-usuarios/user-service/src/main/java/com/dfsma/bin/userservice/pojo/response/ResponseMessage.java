package com.dfsma.bin.userservice.pojo.response;

public class ResponseMessage {
    Integer code=0;
    String description="";

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void ResponseMessage(int httpStatusCode, String reasonPhrase) {
        this.code = httpStatusCode;
        this.description = reasonPhrase;
    }

    public ResponseMessage() {}

    public void clear() {
        this.code=0;
        this.description="";
    }
}
