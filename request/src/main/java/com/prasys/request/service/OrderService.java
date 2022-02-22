package com.prasys.request.service;


import com.prasys.framework.entity.Item;
import com.prasys.framework.entity.PurchaseOrder;
import com.prasys.request.util.EmailUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

@Service
@Transactional
@Configuration
public class OrderService implements Serializable {

    public void sendAttach(List<Item> items, String email) throws MessagingException {
        String host="smtp.gmail.com";
        String subject= "Order";

        String from="prasys8191@gmail.com";
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session=Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("prasys8191@gmail.com", "pomail@8191");
                }
        });

            session.setDebug(true);

            MimeMessage m = new MimeMessage(session);
            try {
                    m.setFrom(from);
                    m.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

                    m.setSubject(subject);

                    EmailUtil emailUtil = new EmailUtil();
                    m.setContent(emailUtil.sendEmail(items));

                    Transport.send(m);


                } catch (Exception e) {
                    e.printStackTrace();
                }


                System.out.println("Sent success...................");

    }
}

