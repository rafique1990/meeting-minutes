package com.example.meetingMinutesdemo.exception.meeting;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MeetingExceptionController {

    @ExceptionHandler(value= MeetingNotFoundException.class)
    public ResponseEntity<Object> meetingNotFoundException(MeetingNotFoundException meetingNotFoundException){
        return  new ResponseEntity<>("Meeting Not FOund", HttpStatus.OK);
    }
}
