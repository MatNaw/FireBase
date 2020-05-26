package com.spdb.firebase.system.import_service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@NoArgsConstructor
public class BrigadesImporter {

    private static final String errorDirectory = File.separator + "errors" + File.separator;
    private static final String finalisingDirectory = File.separator + "finalising" + File.separator;
    private Sheet sheet;

    public List<BrigadeXlsxModel> importData(File file) throws IOException {
        List<BrigadeXlsxModel> data;
        String fileName = file.getName();
        String filePath = file.getPath();
        String errorDirectory = filePath.substring(0, filePath.lastIndexOf(File.separator)) + BrigadesImporter.errorDirectory;
        String finalisingDirectory = filePath.substring(0, filePath.lastIndexOf(File.separator)) + BrigadesImporter.finalisingDirectory;

        try {
            FileInputStream inputStream = new FileInputStream(file);

            Workbook workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheetAt(0);

            data = getDataFromXls();
            workbook.close();
        } catch (Exception e) {
            log.error(String.format("[IMPORT FILE EXCEPTION]: file %s not imported", fileName));
            Files.move(Paths.get(filePath), Paths.get(errorDirectory + fileName), StandardCopyOption.REPLACE_EXISTING);
            return List.of();
        }

        Files.move(Paths.get(filePath), Paths.get(finalisingDirectory + fileName), StandardCopyOption.REPLACE_EXISTING);
        return data;
    }

    private List<BrigadeXlsxModel> getDataFromXls() {
        List<BrigadeXlsxModel> data = new ArrayList<>(List.of());
        int firstRowToRead = 1;
        int lastRowToRead = sheet.getLastRowNum();

        for (int i = firstRowToRead; i <= lastRowToRead; ++i) { // not foreach loop, because we have to omit first row
            try {
                data.add(getBrigadeXlsxModelFromRow(sheet.getRow(i)));
            } catch (Exception e) {
                log.warn(String.format("[ROW READ EXCEPTION]: row number %s could not be read", i));
            }
        }

        return data;
    }

    private BrigadeXlsxModel getBrigadeXlsxModelFromRow(Row row) {
        return BrigadeXlsxModel.builder()
                .name(prepareCellValue(row.getCell(0)))
                .city(prepareCellValue(row.getCell(1)))
                .postalCode(prepareCellValue(row.getCell(2)))
                .street(prepareCellValue(row.getCell(3)))
                .build();
    }

    private String prepareCellValue(Cell cell) {
        return cell != null ? cell.toString() : "";
    }
}
