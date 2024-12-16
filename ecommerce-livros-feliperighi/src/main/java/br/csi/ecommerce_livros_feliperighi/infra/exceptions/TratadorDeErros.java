package br.csi.ecommerce_livros_feliperighi.infra.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity tratarErro400BadRequest(MethodArgumentTypeMismatchException ex){
        return ResponseEntity.badRequest().body(new String("URL mal formatada: "+ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErrDadosInvalidos(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getFieldErrors();
        List<DadosErroValidacao> dados = new ArrayList<>();
        for(FieldError fe : errors){
            dados.add(new DadosErroValidacao(fe.getField(), fe.getDefaultMessage()));
        }
        return ResponseEntity.badRequest().body(dados);
    }

    private record DadosErroValidacao(String campo, String mensagem){}

}
