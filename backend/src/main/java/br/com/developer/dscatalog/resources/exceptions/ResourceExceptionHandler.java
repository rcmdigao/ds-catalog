package br.com.developer.dscatalog.resources.exceptions;

import br.com.developer.dscatalog.services.exceptions.DataBaseException;
import br.com.developer.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError erro = new StandardError();
        erro.setTimestamp(Instant.now());
        erro.setStatus(httpStatus.value());
        erro.setError("Registro não encontrado...");
        erro.setMessage(e.getMessage());
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(erro);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError erro = new StandardError();
        erro.setTimestamp(Instant.now());
        erro.setStatus(httpStatus.value());
        erro.setError("Exceção no banco de dados");
        erro.setMessage(e.getMessage());
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(erro);
    }













}
