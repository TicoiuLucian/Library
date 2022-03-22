package library.service.impl;

public interface EmailSender {

    void sendEmail (String toEmail, String subject, String body);

}
