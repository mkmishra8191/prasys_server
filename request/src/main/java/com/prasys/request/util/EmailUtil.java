package com.prasys.request.util;

import com.prasys.framework.entity.Item;
import com.prasys.framework.entity.PurchaseOrder;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Component

public class EmailUtil {

    public MimeMultipart sendEmail(List<Item> list) {
        MimeMultipart mimeMultipart = new MimeMultipart();

        ByteArrayOutputStream outputStream = null;

        try {
            MimeBodyPart textBodyPart = new MimeBodyPart();

            outputStream = new ByteArrayOutputStream();
            PDFUtil pdfGenerator = new PDFUtil();
            pdfGenerator.writePDF(list,outputStream);
            byte[] bytes = outputStream.toByteArray();

            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            textBodyPart.setText("PFA");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("test.pdf");


            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (Exception ex) {
                }
            }
        }
        return mimeMultipart;
    }

}

