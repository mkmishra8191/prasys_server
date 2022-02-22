package com.prasys.request.util;


import java.io.OutputStream;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.prasys.framework.entity.Item;
import org.springframework.stereotype.Component;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Component
public class PDFUtil {


    public Document writePDF(List<Item> list, OutputStream outputStream) throws DocumentException {
        PdfPTable table = generateTable(list);
        table.setTotalWidth(200);
        table.setLockedWidth(true);
        Rectangle one = new Rectangle(table.getTotalWidth()+16,200);
        Document document = new Document(one,8,8,20,20);

        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.addTitle("PO");
        document.add(table);
        document.close();

        return document;

    }

    public PdfPTable generateTable(List<Item> list) {


        PdfPTable table = new PdfPTable(3);
        table.setTotalWidth(400f);
        table.setLockedWidth( true);

        PdfPCell cell;

        cell = new PdfPCell(new Phrase("PO"));

        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        table.addCell("Item");
        table.addCell("Qty");
        table.addCell("Description");


        list.forEach(item-> {
        table.addCell(String.valueOf(item.getSn()));

        table.addCell(String.valueOf(item.getQuantity()));

        table.addCell(item.getDescription());
        });

        return table;
    }


}
