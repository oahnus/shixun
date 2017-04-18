package top.oahnus.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.oahnus.dto.ResponseData;
import top.oahnus.enums.AuthType;
import top.oahnus.exception.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by oahnus on 2017/2/25.
 * 22:12
 */
@RestControllerAdvice
@Log4j2
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
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
    public ResponseData handleValidatedException(MethodArgumentNotValidException e) {
        return new ResponseData(ServerState.REQUEST_PARAMETER_ERROR, e.getBindingResult().getFieldError().getDefaultMessage());
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
        } else {
            return new ResponseData(ServerState.FAILED, e.getMessage());
        }
    }
}
