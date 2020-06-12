package com.ahmedhassan.technicalassessment.core.presentation.utils;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import retrofit2.HttpException;

public class ApplicationException{

    private Kind kind;

    private ApplicationException(){ }

    public static ApplicationException fromThrowable(Throwable throwable){
        ApplicationException ex = new ApplicationException();
        if(throwable instanceof UnknownHostException || throwable.getCause() instanceof HttpException)
            ex.kind = Kind.NETWORK;
        else if (throwable instanceof TimeoutException || throwable instanceof SocketTimeoutException
                || throwable instanceof ConnectTimeoutException)
            ex.kind = Kind.TIMEOUT;
        else ex.kind = Kind.UNKNOWN;

        return ex;
    }

    public Kind getKind() {
        return kind;
    }

    public enum Kind{
        NETWORK, TIMEOUT, UNKNOWN
    }
}
