package io.github.smallintro.redis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoDataFoundException extends RuntimeException {

    static final long serialVersionUID = 7034897190745766939L;

    public NoDataFoundException(String message) {
        super(message);
    }
}
