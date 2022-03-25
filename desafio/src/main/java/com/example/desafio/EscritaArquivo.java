package com.example.desafio;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class EscritaArquivo {
    public static File escreverArquivo(List < ArquivoLinha > lista ) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        for ( int i = 0; i<lista.size(); i++) {
            Row row =  sheet.createRow( i + 1 );
            row.createCell( 0 ).setCellValue( lista.get( i ).getNome());
            row.createCell( 1 ).setCellValue( lista.get( i ).getIdade());

        }
        File file = File.createTempFile("temp", ".xls");
        try (FileOutputStream fos = new FileOutputStream( file )) {
            workbook.write(fos);
        }
        return file;
    }
}
