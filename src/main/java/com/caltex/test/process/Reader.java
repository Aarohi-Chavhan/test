package com.caltex.test.process;

import com.caltex.test.model.ProductDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class Reader {

    @Autowired
    Writer writer;

    List<ProductDetail> productDetails = new ArrayList<>();
    InputStream inputStream = getClass()
            .getClassLoader().getResourceAsStream(("input.xlsx"));
    SimpleDateFormat format = new SimpleDateFormat("MMM-yy");

    public void readExcelFile() throws IOException, InvalidFormatException {
        log.info("Reading input excel file");
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Iterator<Cell>cellIterator = currentRow.iterator();
            //Read the first 3 columns as from 4th column we need to create new ProductDetail object for unpivot
            String category = currentRow.getCell(0).getStringCellValue();
            String productName = currentRow.getCell(1).getStringCellValue();
            String units = currentRow.getCell(2).getStringCellValue();
            for (int i = 3; i < currentRow.getPhysicalNumberOfCells(); i++) {
                if(currentRow.getCell(i).getNumericCellValue() == 0.0)
                    continue;
                productDetails.add(new ProductDetail(category, productName, getDate(sheet.getRow(0).getCell(i)), units, currentRow.getCell(i).getNumericCellValue()));
            }
        }
        log.info("Reading file complete. Output csv writing in progress!!");
        writer.csvWriter(productDetails);
    }

    private String getDate(Cell cell){
        String date = "";
        if(cell.getCellType() ==  CellType.NUMERIC){
            if(DateUtil.isCellDateFormatted(cell))
                date = format.format(cell.getDateCellValue());
            else
                date = String.valueOf(cell.getNumericCellValue());
        }
        return date;
    }
}
