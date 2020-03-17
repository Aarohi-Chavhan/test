package com.caltex.test;

import com.caltex.test.model.ProductDetail;
import com.caltex.test.process.Reader;
import com.caltex.test.process.Writer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = TestApplication.class)
class TestApplicationTests {

	@Autowired
	Writer writer;

	@Test
	public void testIFCsvGenerated() throws IOException, InvalidFormatException {
		List<ProductDetail> list = new ArrayList<>();
		list.add(new ProductDetail("Physical", "Term", "Jun-19", "bbl", 0.2));
		list.add(new ProductDetail("Physical", "Term", "Jul-19", "bbl", 5.8));
		list.add(new ProductDetail("Physical", "Term", "Aug-19", "bbl", 5.6));

		writer.csvWriter(list);

		File file = new File("C:/Users/Public/output.csv");
		Assertions.assertTrue(file.exists());
	}

}
