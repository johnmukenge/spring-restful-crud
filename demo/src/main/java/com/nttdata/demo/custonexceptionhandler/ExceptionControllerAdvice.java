package com.nttdata.demo.custonexceptionhandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * Per personalizzare il body della response in base al tipo di eccezione sollevata.
 * 
 */

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());
		body.put("message", ex.getMessage());

		System.out.println("ENTRO?");
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	/*
	 * OPPURE, USANDO LA CLASSE DI TEMPLATE ApiError:
	 */

//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//
//		Map<String, String> errors = new HashMap<>();
//
//		ex.getBindingResult().getAllErrors().forEach(error -> {
//			String fieldName = ((FieldError) error).getField();
//			String errorMessage = error.getDefaultMessage();
//			errors.put(fieldName, errorMessage);
//		});
//
//		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex, ex.getMessage(), errors);
//		System.out.println("ENTRO?");
//		return new ResponseEntity<>(apiError, apiError.getHttpStatus());
//	}

	/*
	 * Gestione custom di altre eccezioni
	 */

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {

		Map<String, String> errors = new HashMap<>();

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex, ex.getMessage(), errors);
		System.out.println("SONO ENTRATO");
		return new ResponseEntity<>(apiError, apiError.getHttpStatus());

	}

//	@Override
//	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
//			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//
//		Map<String, String> errors = new HashMap<>();
//
//		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex, ex.getMessage(), errors);
//
//		return new ResponseEntity<>(apiError, apiError.getHttpStatus());
//
//	}
//
//	@ExceptionHandler(RecordNotFoundException.class)
//	public ResponseEntity<Object> handleRecordNotFound(RecordNotFoundException ex) {
//
//		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex, ex.getMessage(), ex.getErrors());
//
//		return new ResponseEntity<>(apiError, apiError.getHttpStatus());
//
//	}
//
//	@ExceptionHandler(IncorrectServiceException.class)
//	public ResponseEntity<Object> handleIncorrectService(IncorrectServiceException ex) {
//
//		ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, ex, ex.getMessage(), ex.getErrors());
//
//		return new ResponseEntity<>(apiError, apiError.getHttpStatus());
//
//	}
//
//	@ExceptionHandler(DataIntegrityViolationException.class)
//	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
//
//		Map<String, String> errors = new HashMap<>();
//
//		ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex, ex.getMessage(), errors);
//
//		return new ResponseEntity<>(apiError, apiError.getHttpStatus());
//
//	}

}
