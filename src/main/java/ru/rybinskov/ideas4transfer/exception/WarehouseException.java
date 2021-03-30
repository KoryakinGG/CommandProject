package ru.rybinskov.ideas4transfer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WarehouseException extends Exception{

	public WarehouseException(String message){
    	super(message);
    }
}
