package com.example.desafio;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
public class Excecao {

    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<String> tratamentoFormatoInvalido(){
    return ResponseEntity.badRequest().body("O Arquivo enviado deve estar no formato Excel ");
    }
    @ExceptionHandler({MultipartException.class})
    public ResponseEntity<String> tratamentoMultPart(){
        return ResponseEntity.badRequest().body("Voce deve enviar um arquivo");
    }
}

