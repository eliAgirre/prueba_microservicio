package com.ibm.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

import com.ibm.models.Error;

@Getter
public class ServiceException extends RuntimeException {

    private final String code;
    private final HttpStatus httpStatus;
    private final Map<String, String> params;
    private final Error error;

    public ServiceException(String code, HttpStatus httpStatus, String message, Throwable cause,
                             Map<String, String> params) {
        super(message, cause);
        this.code = code;
        this.httpStatus = httpStatus;
        this.params = params;

        this.error = Error.builder().code(code).message(message)
                .errors(cause != null ? Collections.singletonList(cause.getMessage()) : null).build();
    }

    public static class Builder {
        private String code;
        private String message;
        private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        private Throwable cause;
        private Map<String, String> params;

        public Builder(String code) {
            this.code = code;
        }

        public Builder withHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Build a ServiceException
         *
         * @return the ServiceException
         */
        public ServiceException build() {
            return new ServiceException(this.code, this.httpStatus, this.message, this.cause, this.params);
        }
    }

}
