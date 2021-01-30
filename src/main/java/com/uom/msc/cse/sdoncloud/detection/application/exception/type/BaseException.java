
package com.uom.msc.cse.sdoncloud.detection.application.exception.type;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BaseException extends Exception{
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BaseException(String message) {

        super(message);
    }

    public BaseException(String message, String code) {

        super(message);
        this.code = code;
    }
}
