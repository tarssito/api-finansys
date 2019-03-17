package br.com.tarssito.financys.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    /**
     * Exceção a ser lançada de acesso negado
     * @param e {exception captured}
     * @param request
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<StandardError> authorization(AccessDeniedException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Acesso negado",
                e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }

    /**
     *
     * Exceção a ser lançada quando a validação em um argumento anotado com @Valid falhar
     *
     * @param e {exception captured}
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de Validação", e.getMessage(), request.getRequestURI());
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            err.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }
}
