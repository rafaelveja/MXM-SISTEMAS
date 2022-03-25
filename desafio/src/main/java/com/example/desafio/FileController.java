package com.example.desafio;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
public class FileController {

    @Autowired
    ProcessamentoArquivoService service;

    @PostMapping("/file")
    public ResponseEntity < InputStreamResource > processarArquivo (@RequestParam("file") MultipartFile arquivo,@RequestParam("ordem") CriterioOrdenacao ordem) throws IOException {
        Instant inicio= Instant.now();
        Sheet worksheet=service.getSheetApropriado(arquivo);
        List<ArquivoLinha> linhas = service.processarArquivo(worksheet);
        if (ordem==CriterioOrdenacao.NOME){
            linhas.sort(Comparator.comparing(ArquivoLinha::getNome));
            File file = EscritaArquivo.escreverArquivo( linhas );
            InputStreamResource resource = new InputStreamResource ( new FileInputStream( file ));
            return ResponseEntity.ok().contentLength( file.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body( resource );
        }
        linhas.sort(Comparator.comparing(ArquivoLinha::getIdade));
        File file = EscritaArquivo.escreverArquivo( linhas );
        InputStreamResource resource = new InputStreamResource ( new FileInputStream( file ));
        Instant fim= Instant.now();
        return ResponseEntity.ok().contentLength( file.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body( resource );


    }
}
