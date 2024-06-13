package com.arianit.tripbooking.exception;

public class ReservationAlreadyExists extends RuntimeException{
    public ReservationAlreadyExists (String msg){
        super(msg);
    }
}
