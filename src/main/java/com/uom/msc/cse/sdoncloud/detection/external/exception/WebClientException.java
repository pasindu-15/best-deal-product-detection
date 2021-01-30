
package com.uom.msc.cse.sdoncloud.detection.external.exception;

import com.uom.msc.cse.sdoncloud.detection.application.exception.type.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class WebClientException extends BaseException {
    public WebClientException(String message) {
        super(message);
    }

    public WebClientException(String message, String code) {
        super(message, code);
    }
}
