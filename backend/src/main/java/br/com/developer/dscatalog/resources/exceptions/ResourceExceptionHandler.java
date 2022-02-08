package br.com.developer.dscatalog.resources.exceptions;

import br.com.developer.dscatalog.services.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
        StandardError erro = new StandardError();
        erro.setTimestamp(Instant.now());
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setError("Registro não encontrado...");
        erro.setMessage(e.getMessage());
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }


}
