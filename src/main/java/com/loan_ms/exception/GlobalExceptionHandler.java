package com.loan_ms.exception;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.loan_ms.constants.LoansConstants;
import com.loan_ms.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	   @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
	            WebRequest request) {
	        Map<String, String> validationErrors = new HashMap();
	        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

	        validationErrorList.forEach((error) -> {
	            String fieldName = ((FieldError) error).getField();
	            String validationMsg = error.getDefaultMessage();
	            validationErrors.put(fieldName, validationMsg);
	        });
	        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	    
	   }
	   
	   @ExceptionHandler(Exception.class)
	   public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception e,WebRequest webRequest){
		   ErrorResponseDto dto = new ErrorResponseDto(webRequest.getDescription(false),
				   HttpStatus.INTERNAL_SERVER_ERROR, LoansConstants.MESSAGE_500, LocalDateTime.now());
		   
		   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				   .body(dto);
	   }
	   
	   @ExceptionHandler(ResourceNotFoundException.class)
	   public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
			   WebRequest webRequest){
		   ErrorResponseDto  dto = new ErrorResponseDto();
		   dto.setApiPath(webRequest.getDescription(false));
		   dto.setErrorCode(HttpStatus.NOT_FOUND);
		   dto.setErrorMsg(exception.getMessage());
		   dto.setErrorTime(LocalDateTime.now());
		   
		   return ResponseEntity.status(HttpStatus.NOT_FOUND)
				   .body(dto);
	   }
	   
	   
	   @ExceptionHandler(LoanAlreadyExistsException.class)
	   public ResponseEntity<ErrorResponseDto> handleLoanAlreadyExistsException(LoanAlreadyExistsException exception,
			   WebRequest webRequest){
		   
		   ErrorResponseDto  dto = new ErrorResponseDto();
		   dto.setApiPath(webRequest.getDescription(false));
		   dto.setErrorCode(HttpStatus.ALREADY_REPORTED);
		   dto.setErrorMsg(LoansConstants.MESSAGE_500);
		   dto.setErrorTime(LocalDateTime.now());
		   
		   return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
				   .body(dto);
	   }
	   
}