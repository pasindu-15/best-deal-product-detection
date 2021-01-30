
package com.uom.msc.cse.sdoncloud.detection.domain.exception;

import com.adl.et.telco.dte.template.baseapp.application.exception.type.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DomainException extends BaseException {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, String code) {
        super(message, code);
    }
}
