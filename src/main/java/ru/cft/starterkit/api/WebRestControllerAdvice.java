package ru.cft.starterkit.api;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.cft.starterkit.ServiceException;
import ru.cft.starterkit.entity.ErrorCode;
import ru.cft.starterkit.entity.ErrorResponse;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleNotFoundException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();

        if (exception instanceof ServiceException) {
            ServiceException serviceException = (ServiceException)exception;
            errorResponse.setCode(serviceException.getErrorCode().getCode());
            errorResponse.setMessage(exception.getMessage());
            return errorResponse;
        } else {
            errorResponse.setCode(ErrorCode.SERVER_INTERNAL.getCode());
            errorResponse.setMessage("Server internal error");
            return errorResponse;
        }
    }


}
