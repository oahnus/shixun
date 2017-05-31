package top.oahnus.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import top.oahnus.payload.ResponseData;
import top.oahnus.enums.ServerState;
import top.oahnus.exception.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by oahnus on 2017/2/25.
 * 22:12
 */
@RestControllerAdvice
@Log4j2
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData handleException(Exception e){
        try (StringWriter stringWriter = new StringWriter();
             PrintWriter printWriter = new PrintWriter(stringWriter)) {
            e.printStackTrace(printWriter);
            log.error("ERROR: {}", stringWriter.toString());
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        return new ResponseData(ServerState.INNER_SERVER_ERROR, e.getClass().getSimpleName());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData handleValidatedException(MethodArgumentNotValidException e) {
        return new ResponseData(ServerState.REQUEST_PARAMETER_ERROR, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return new ResponseData(ServerState.REQUEST_PARAMETER_ERROR, "请求参数类型错误");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return new ResponseData(ServerState.REQUEST_PARAMETER_ERROR, e.getMessage());
    }
    
    @ExceptionHandler(ClientException.class)
    public ResponseData handleClientException(ClientException e) {
        if (e instanceof NoAuthException) {
            return new ResponseData(ServerState.NO_AUTH_ERROR, e.getMessage());
        } else if (e instanceof NotFoundException) {
            return new ResponseData(ServerState.DATA_NOT_FOUND_ERROR, e.getMessage());
        } else if (e instanceof DataExistedException) {
            return new ResponseData(ServerState.DATA_EXISTED_ERROR, e.getMessage());
        } else if (e instanceof SQLExecuteFailedExceeption) {
            return new ResponseData(ServerState.SQL_EXECUTE_FAILED, e.getMessage());
        } else if (e instanceof ReadDataFailedException) {
            return new ResponseData(ServerState.LOGIN_FAILED_ERROR, e.getMessage());
        } else if (e instanceof FileUplaodException) {
            return new ResponseData(ServerState.FILE_UPLOAD_ERROR, e.getMessage());
        } else if (e instanceof LoginFailedException) {
            return new ResponseData(ServerState.READ_DATA_FAILED, e.getMessage());
        } else if (e instanceof DataStatusException) {
            return new ResponseData(ServerState.DATA_STATUS_NOT_ALLOWED, e.getMessage());
        } else if (e instanceof BadRequestParamException) {
            return new ResponseData(ServerState.REQUEST_PARAMETER_ERROR, e.getMessage());
        } else if (e instanceof TimeSpanException) {
            return new ResponseData(ServerState.TIME_SPAN_ERROR, e.getMessage());
        } else {
            return new ResponseData(ServerState.FAILED, e.getMessage());
        }
    }
}
