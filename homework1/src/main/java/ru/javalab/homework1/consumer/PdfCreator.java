package ru.javalab.homework1.consumer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import ru.javalab.homework1.dto.DocumentDto;

public class PdfCreator {

    private static final Font headFont = FontFactory.getFont(FontFactory.COURIER);

    private static PdfPCell generatePdfHeaderCell(String text) {
        PdfPCell hcell = new PdfPCell(new Phrase(text, headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return hcell;
    }

    private static PdfPCell generatePdfTableCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, headFont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    public static Document generatePdf(String theme, DocumentDto documentDto, Document document) throws DocumentException {
        Paragraph purpose = new Paragraph(theme);
        purpose.setSpacingAfter(10);
        document.add(purpose);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(80);
        table.setWidths(new int[]{4, 4, 4, 4, 4});

        table.addCell(generatePdfHeaderCell("Name:"));
        table.addCell(generatePdfHeaderCell("Surname:"));
        table.addCell(generatePdfHeaderCell("Passport series:"));
        table.addCell(generatePdfHeaderCell("Passport number:"));
        table.addCell(generatePdfHeaderCell("Date:"));

        table.addCell(generatePdfTableCell(documentDto.getName()));
        table.addCell(generatePdfTableCell(documentDto.getSurname()));
        table.addCell(generatePdfTableCell(String.valueOf(documentDto.getPassportSeries())));
        table.addCell(generatePdfTableCell(String.valueOf(documentDto.getPassportNumber())));
        table.addCell(generatePdfTableCell(documentDto.getDate().toString()));
        document.add(table);
        return document;
    }


}
