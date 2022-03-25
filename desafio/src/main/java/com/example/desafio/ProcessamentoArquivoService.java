package com.example.desafio;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProcessamentoArquivoService {
    public List<ArquivoLinha> processarArquivo(Sheet planilha) {
        List<ArquivoLinha> linhas = new ArrayList<>();
        for (int i = 0; i < planilha.getPhysicalNumberOfRows(); i++) {
            if (planilha.getRow(i).getCell(0) != null && planilha.getRow(i).getCell(1) != null) {
                ArquivoLinha linha = new ArquivoLinha();
                linha.setIdade((int) planilha.getRow(i).getCell(1).getNumericCellValue());
                linha.setNome(planilha.getRow(i).getCell(0).getStringCellValue());
                linhas.add(linha);
            }
        }
        return linhas;
    }

    public Workbook getWorkBookApropriado(MultipartFile arquivo) throws IOException {
        switch (arquivo.getOriginalFilename().substring(arquivo.getOriginalFilename().lastIndexOf(".") + 1)) {
            case "xls":
                return new HSSFWorkbook(arquivo.getInputStream());
            case "xlsx":
                return new XSSFWorkbook(arquivo.getInputStream());
            default:
                return null;
        }

    }

    public Sheet getSheetApropriado(MultipartFile arquivo) throws IOException {
        return Objects.requireNonNull( getWorkBookApropriado(arquivo)).getSheetAt(0);

    }

}
