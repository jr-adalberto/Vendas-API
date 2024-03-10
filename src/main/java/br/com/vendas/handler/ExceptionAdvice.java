package br.com.vendas.handler;

import br.com.vendas.domain.ApiException;
import br.com.vendas.domain.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleDefaultException(Exception ex) {
        return new ResponseEntity<>(obtemErro(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleFieldValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ErrorResponse> listaErro = bindingResult.getFieldErrors().stream()
                .map(erro -> new ErrorResponse(erro.getField(), erro.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(listaErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleSpartaccusException(ApiException ex) {
        if (HttpStatus.UNAUTHORIZED.equals(ex.getStatus()))
            return new ResponseEntity<>(obtemErro(ex), HttpStatus.UNAUTHORIZED);

        if (HttpStatus.FORBIDDEN.equals(ex.getStatus()))
            return new ResponseEntity<>(obtemErro(ex), HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(obtemErro(ex), HttpStatus.BAD_REQUEST);
    }

    private List<ErrorResponse> obtemErro(Exception ex) {
        List<ErrorResponse> listaDTO = new ArrayList<>();
        ErrorResponse dto = new ErrorResponse();
        dto.setMessage(ex.getMessage());
        listaDTO.add(dto);

        return listaDTO;
    }


}