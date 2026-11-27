package sisco.ass03;

import java.io.File;
import java.net.URL;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PDFBoxExample {

	public static void main(String[] args) {
        try {
			URL url = PDFBoxExample.class.getResource("/sisco/ass03/example.pdf");
			File file = new File(url.toURI());
			PDDocument document = Loader.loadPDF(file);
			int pages = document.getNumberOfPages();
	        System.out.println("Number of pages: " + pages);
	        document.close();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }

	}

}
