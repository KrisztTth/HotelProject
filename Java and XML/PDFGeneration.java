package hotel;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import hotel.Vendeg;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PDFGeneration {

	public void pdfGeneration(String fajlnev, ObservableList<Vendeg> data) {

		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(fajlnev + ".pdf"));
			document.open();
			Image image1 = Image.getInstance(getClass().getResource("/hotel_logo.png"));
			image1.scaleToFit(300, 172);
			image1.setAbsolutePosition(170f, 650f);
			document.add(image1);

			document.add(new Paragraph("\n\n\n\n\n\n\n\n\n"));

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Információ");
			alert.setContentText("Sikeres konvertálás");

			alert.showAndWait();

			// Táblázat

			Font f1 = FontFactory.getFont("Mali.ttf", "Cp1250", true);
			float[] columnWidth = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
			PdfPTable table = new PdfPTable(columnWidth);
			table.setWidthPercentage(110);
			PdfPCell cell = new PdfPCell(new Phrase("Foglalások listája", f1));
			cell.setBackgroundColor(GrayColor.GRAYWHITE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(30);

			cell.setColspan(9);
			table.addCell(cell);

			table.getDefaultCell().setBackgroundColor(new GrayColor(0.8f));
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(new Phrase("Vezetéknév", f1));
			table.addCell(new Phrase("Keresztnév", f1));
			table.addCell(new Phrase("Cím", f1));
			table.addCell(new Phrase("Elérhet\u0151ség", f1));
			table.addCell(new Phrase("Bejelentkezés", f1));
			table.addCell(new Phrase("Kijelentkezés", f1));
			table.addCell(new Phrase("Szobaszám", f1));
			table.addCell(new Phrase("Szobatípus", f1));
			table.addCell(new Phrase("F\u0151", f1));
			table.setHeaderRows(1);

			table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			for (int i = 1; i <= data.size(); i++) {
				Vendeg actualVendeg = data.get(i - 1);

				table.addCell(new Phrase(actualVendeg.getVezeteknev(), f1));
				table.addCell(new Phrase(actualVendeg.getKeresztnev(), f1));
				table.addCell(new Phrase(actualVendeg.getCim(), f1));
				table.addCell(new Phrase(String.valueOf(actualVendeg.getTelszam()), f1));
				table.addCell(new Phrase(actualVendeg.getBejelentkezes(), f1));
				table.addCell(new Phrase(actualVendeg.getKijelentkezes(), f1));
				table.addCell(new Phrase(actualVendeg.getSzobaszam(), f1));
				table.addCell(new Phrase(actualVendeg.getSzobatipus(), f1));
				table.addCell(new Phrase(actualVendeg.getFo(), f1));
			}

			document.add(table);

			// Aláírás

			Paragraph base = new Paragraph(new Phrase("\n\nGenerálva a Hotelfoglalási rendszer segítségével.", f1));

			document.add(base);

		} catch (Exception e) {
			e.printStackTrace();
		}

		document.close();
	}

}
