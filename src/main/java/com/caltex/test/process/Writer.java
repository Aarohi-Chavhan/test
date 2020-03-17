package com.caltex.test.process;

import com.caltex.test.model.ProductDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class Writer {
    private static final String OUTPUT_CSV_FILE = "/Users/Public/output.csv";

    public void csvWriter(List<ProductDetail> listOfProducts) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(OUTPUT_CSV_FILE));

        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.newFormat('|')
                .withHeader("category", "product_name", "Date", "units", "value").withRecordSeparator(System.getProperty("line.separator")));
        listOfProducts.stream().forEach(
                product -> {
                    try {
                        csvPrinter.printRecord(Arrays.asList(product.getCategory(), product.getProductName(), product.getDate(), product.getUnits(), product.getValue()));
                    } catch (IOException e) {
                        log.error("Error writing CSV file!!");
                    }
                });
        csvPrinter.flush();
        log.info("Output csv file generated at: "+OUTPUT_CSV_FILE);
    }
}
