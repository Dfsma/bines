package com.dfsma.bin.userservice.pojo.response;

import com.dfsma.bin.userservice.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

@Getter
@Setter
public class ResponseMessage {
    Integer code=0;
    String description="";

    UserResponse userResponse;

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

    public void ResponseMessage(int httpStatusCode,String reasonPhrase, UserResponse userResponse) {
        this.code = httpStatusCode;
        this.description = reasonPhrase;
        this.userResponse = userResponse;
    }
    public ResponseMessage() {}

    public void clear() {
        this.code=0;
        this.description="";
    }
}
