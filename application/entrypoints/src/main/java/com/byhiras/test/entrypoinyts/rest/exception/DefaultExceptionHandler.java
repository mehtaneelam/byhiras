package com.byhiras.test.entrypoinyts.rest.exception;

import com.byhiras.test.core.usercase.exceptions.InvalidBidAmountException;
import com.byhiras.test.core.usercase.exceptions.ItemNotFoundException;
import com.byhiras.test.core.usercase.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> itemNotFound(){

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFound(){
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidBidAmountException.class)
    public ResponseEntity<Object> invalidAmount(){
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> anyRuntimeException(){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
