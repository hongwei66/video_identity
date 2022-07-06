package com.longma.videoidenty.common;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 8177301895983119239L;
    String errorCode;
    String errorMsg;
    private String requestID;

    public BaseException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BaseException(String errorCode, String errorMsg, String requestID) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.requestID = requestID;
    }

    public BaseException(SystemErrorEnum error) {
        super(error.getMsg());
        this.errorCode = error.getCode();
        this.errorMsg = error.getMsg();
    }

    public BaseException(SystemErrorEnum error, String requestID) {
        super(error.getMsg());
        this.errorCode = error.getCode();
        this.errorMsg = error.getMsg();
        this.requestID = requestID;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getRequestID() {
        return this.requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

}
