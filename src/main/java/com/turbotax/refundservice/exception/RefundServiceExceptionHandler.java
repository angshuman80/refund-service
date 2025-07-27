package com.turbotax.refundservice.exception;

import com.turbotax.refundservice.model.ErrorCode;
import com.turbotax.refundservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RefundServiceExceptionHandler {
        /**
         * Handles ResourceNotFoundException and returns a 404 Not Found response.
         *
         * @param ex the exception
         * @return ResponseEntity with error message and HTTP status
         */
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
            ErrorResponse response = new ErrorResponse(ErrorCode.RESOURCE_NOT_FOUND, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
            ErrorResponse response = new ErrorResponse(ErrorCode.BAD_REQUEST, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
            ErrorResponse response = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
