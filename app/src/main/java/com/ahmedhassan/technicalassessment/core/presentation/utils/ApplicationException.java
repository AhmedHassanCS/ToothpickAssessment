package com.ahmedhassan.technicalassessment.core.presentation.utils;

import com.ahmedhassan.technicalassessment.R;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import retrofit2.HttpException;
/**
 * Is to handle regular errors
 * In our app's case it's so simplified, because no server error returned
 * */
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


    public int getMessageResource(){
         switch (kind){
            case NETWORK:
                return R.string.network_error;
            case TIMEOUT:
                return R.string.timeout_error;
             default:
                return R.string.unknown_error;
        }
    }
    public enum Kind{
        NETWORK, TIMEOUT, UNKNOWN
    }
}
