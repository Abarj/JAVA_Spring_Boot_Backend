package com.example.block22SpringAdvanced.infrastructure.services;

import com.example.block22SpringAdvanced.domain.entities.jpa.CustomerEntity;
import com.example.block22SpringAdvanced.infrastructure.abstract_services.IReportService;
import com.example.block22SpringAdvanced.domain.repositories.jpa.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

@Service
@AllArgsConstructor
@Slf4j
public class ExcelService implements IReportService {

    private final CustomerRepository customerRepository;

    @Override
    public byte[] readFile() {
        try {
            this.createReport();
            var path = Paths.get(REPORTS_PATH, String.format(FILE_NAME, LocalDate.now().getMonth())).toAbsolutePath();
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void createReport() {

        var workBook = new XSSFWorkbook();
        var sheet = workBook.createSheet(SHEET_NAME);

        sheet.setColumnWidth(0, 5000);
        sheet.setColumnWidth(1, 7000);
        sheet.setColumnWidth(2, 3000);

        var header = sheet.createRow(0);
        var headerStyle = workBook.createCellStyle();

        headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        var font = workBook.createFont();

        font.setFontName(FONT_TYPE);
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);

        headerStyle.setFont(font);

        var headerCell = header.createCell(0);
        headerCell.setCellValue(COLUMN_CUSTOMER_ID);
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue(COLUMN_CUSTOMER_NAME);
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue(COLUMN_CUSTOMER_PURCHASES);
        headerCell.setCellStyle(headerStyle);

        var style = workBook.createCellStyle();
        style.setWrapText(true);

        var customers = customerRepository.findAll();
        var rowPos = 1;

        for (CustomerEntity customer: customers) {
            var row = sheet.createRow(rowPos);
            var cell = row.createCell(0);
            cell.setCellValue(customer.getDni());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(customer.getFullName());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(getTotalPurchases(customer));
            cell.setCellStyle(style);

            rowPos++;
        }

        var report = new File(String.format(REPORTS_PATH_WITH_NAME, LocalDate.now().getMonth()));
        var path = report.getAbsolutePath();
        var fileLocation = path + FILE_TYPE;

        try (var outputStream = new FileOutputStream(fileLocation)) {
            workBook.write(outputStream);
            workBook.close();
        } catch (IOException e) {
            log.error("Could not generate excel file", e);
            throw new RuntimeException();
        }
    }

    private static int getTotalPurchases(CustomerEntity customer) {
        return customer.getTotalLodgings() + customer.getTotalFlights() + customer.getTotalTours();
    }

    private static final String SHEET_NAME = "Customer total sales";
    private static final String FONT_TYPE = "Arial";
    private static final String COLUMN_CUSTOMER_ID = "id";
    private static final String COLUMN_CUSTOMER_NAME = "name";
    private static final String COLUMN_CUSTOMER_PURCHASES = "purchases";
    private static final String REPORTS_PATH_WITH_NAME = "reports/Sales-%s";
    private static final String REPORTS_PATH = "reports";
    private static final String FILE_TYPE= ".xlsx";
    private static final String FILE_NAME= "Sales-%s.xlsx";
}
