package top.oahnus.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import top.oahnus.common.dto.ResultData;
import top.oahnus.enums.ErrorType;
import top.oahnus.exception.NoAuthException;
import top.oahnus.exception.NotFoundException;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常结果的处理
 */
@Slf4j
@ControllerAdvice
public class ExceptionAdvice {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultData processException(Exception e) throws Exception {
        logError(e);
        return new ResultData(ErrorType.UNKNOWN_ERROR, "未知的错误。");
    }


    private static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    private void logError(Exception e) {
        logError(e, true);
    }

    private void logError(Exception e, Boolean withStack) {
        try (StringWriter stringWriter = new StringWriter();
             PrintWriter printWriter = new PrintWriter(stringWriter)) {
            e.printStackTrace(printWriter);
            log.error(stringWriter.toString());
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
    // ////////////

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData processValidationError(MethodArgumentNotValidException e) throws Exception {
        logError(e);
        return new ResultData(ErrorType.INVALID_PARAM_ERROR, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData processMethodArgumentTypeError(MethodArgumentTypeMismatchException e) throws Exception {
        logError(e);
        return new ResultData(ErrorType.INVALID_METHOD_ERROR, e.getMessage());
    }

    /**
     * 401
     * 没有权限
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = NoAuthException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResultData processAuthError(NoAuthException e) throws Exception {
        log.info("NoAuthException,e={}", e.getMessage());
        return new ResultData(ErrorType.NO_AUTH_ERROR, e.getMessage());
    }

    /**
     * 资源不存在
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResultData processNotFoundError(NotFoundException e) throws Exception {
        log.info("NotFoundException,e={}", e.getMessage());
        return new ResultData(ErrorType.NOT_FOUND_ERROR, e.getMessage());
    }

    /**
     * ================================
     * 框架内置 异常
     * ================================
     */


    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ResultData> handleClientException(MultipartException e) {
        logError(e);

        ResultData ResultData = new ResultData(ErrorType.FILE_UPLOAD_ERROR, e.getMessage());
        return new ResponseEntity<>(ResultData,
                getHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * 数据库 读写错误
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = PersistenceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData processPersistenceError(PersistenceException e) throws Exception {
        logError(e);
        return new ResultData(ErrorType.DATABASE_ERROR, e.getMessage());
    }

    /**
     * 缺少参数
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData processMissingParamError(MissingServletRequestParameterException e) throws Exception {
        log.info("MissingServletRequestParameterException,e={}", e.getMessage());
        return new ResultData(ErrorType.INVALID_PARAM_ERROR, "缺少`" + e.getParameterName() + "`参数。");
    }

    /**
     * 请求方法不支持
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData processNotSupportMethodError(HttpRequestMethodNotSupportedException e) {
        log.info("MissingServletRequestParameterException,e={}", e.getMessage());
        return new ResultData(ErrorType.INVALID_METHOD_ERROR, "请求方法不正确。");
    }

    /**
     * MediaType 无法处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMediaTypeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData processUnsupportedMediaError(HttpMediaTypeException e) {
        log.info("HttpMediaTypeException,e={}", e.getMessage());
        return new ResultData(ErrorType.UNSUPPORTED_MEDIA, "请使用`JSON`格式传输数据。");
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ResultData processHttpRequestBodyError(HttpMessageNotReadableException e) {
        logError(e);
        return new ResultData(ErrorType.INVALID_PARAM_ERROR, e.getMessage());
    }
}
