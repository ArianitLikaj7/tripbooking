package com.arianit.tripbooking.exception;

public class IlegalNumberOfSeatsException extends RuntimeException{
    public IlegalNumberOfSeatsException(String msg){
        super(msg);
    }
}
