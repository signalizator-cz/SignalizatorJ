package org.praguehacks.signalizatorJ;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CharSequenceInputStream;
import org.apache.commons.mail.*;

import org.hibernate.cfg.ClassPropertyHolder;
import org.omg.IOP.Encoding;
import org.praguehacks.signalizatorJ.database.EmailTemplate;
import org.praguehacks.signalizatorJ.database.EmailTemplateManager;
import org.praguehacks.signalizatorJ.database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.*;
import java.io.*;
import java.net.InetAddress;
import java.util.Properties;


/**
 * Created by kucerajn on 14.6.2015.
 */
@Service
public class SendEmail {
    @Autowired
    EmailTemplateManager emailTemplateManager;

    @Autowired
    WebAppConfig webAppConfig;
    
    private User user;
    private String from;
    private String textBody;
    private String emailType; // "Welcome", "Weekly"
    private String encoding = "UTF-8";

    private static final String TYPE_WELCOME = "Welcome";
    private static final String TYPE_WEEKLY = "Weekly";
    public static final String[] TYPE_VALUES = new String[] {TYPE_WELCOME, TYPE_WEEKLY };
    public static final Set<String> EMAIL_TYPE_SET = new HashSet<String>(Arrays.asList(TYPE_VALUES));

    public SendEmail() {}
    
    public SendEmail(User user) {
        this.user = user;
    }

    public String send() throws EmailException, IOException {
        if (!EMAIL_TYPE_SET.contains(emailType)) {
            throw new UnsupportedOperationException("Email type has to be specified as one of: " + EMAIL_TYPE_SET);
        }
        
        HtmlEmail email = new HtmlEmail();
        email.setCharset("UTF-8");
        email.setSmtpPort(webAppConfig.getEmailFromPort());
        email.setHostName(webAppConfig.getEmailFromSmtpServer());
        email.setAuthenticator(new DefaultAuthenticator(webAppConfig.getEmailFromUsername(),
                webAppConfig.getEmailFromPassword()));
        email.setStartTLSEnabled(true);
        email.setFrom(webAppConfig.getEmailFromUsername());
        email.addTo(user.getEmail());

        List<EmailTemplate> templates = null;
        try {
            templates = emailTemplateManager.findAllByType(emailType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (templates != null && templates.size() > 0) {
            EmailTemplate template = templates.get(0);
            email.setSubject(template.getSubject());
            email.setHtmlMsg(template.getHtmlBody());
            email.setTextMsg(template.getTextBody());
            
        } else {
            email.setSubject("Signalizátor: notifikace z ulice");
            email.setTextMsg("Vítejte v Singnalizátoru,\n \nod teď Vám budeme pravidelně zasílat výpis oznámení z úředních desek týkající se vybrané oblasti.\n\nVáš\nTým Signalizátor\n\nSignalizátor na síti:\nhttps://twittercom/damesignal\n\nKontaktní informace:\nEmail: damesignal@gmail.com\nwww.signalizator.cz");
        }

        String sendEmailResult;
        try {
            sendEmailResult = email.send();
//            System.out.println("INFO: Email sent to " + email.getToAddresses());
        } catch (EmailException e) {
            sendEmailResult = e.getMessage();
            e.printStackTrace();
        }
        return sendEmailResult;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public static String getTypeWelcome() {
        return TYPE_WELCOME;
    }

    public static String getTypeWeekly() {
        return TYPE_WEEKLY;
    }

    public void setTypeWelcome() {
        emailType = TYPE_WELCOME;
    }
    
    public void setTypeWeekly() {
        emailType = TYPE_WEEKLY;
    }
}
