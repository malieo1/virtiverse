package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Participation;
import com.example.virtiverse.serviceInterface.IEmailEventService;
import com.example.virtiverse.serviceInterface.IEventService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceEmailEvent implements IEmailEventService {
    IEventService iEventService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private JavaMailSender emailSender;

    public void sendConfirmationEmailWithQRCode(Participation participation) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Créer le contenu du mail de confirmation avec le QR code
            String mailContent = "Merci pour votre participation à l'événement \"" + participation.getEvent().getNomEvent() + "\".\n" +
                    "Votre participation a été enregistrée avec succès. Voici les détails :\n" +
                    "Nom de l'événement : " + participation.getEvent().getNomEvent() + "\n" +
                    "Date de début : " + participation.getEvent().getDateDebutEvent() + "\n" +
                    "Lieu : " + participation.getEvent().getLieuEvent() + "\n" +
                    // Ajoutez d'autres détails de l'événement ici
                    "\n" +
                    "Merci encore pour votre participation !";

            // Ajouter le contenu du mail
            helper.setText(mailContent);

            // Ajouter le QR code en pièce jointe
            byte[] qrCodeBytes = iEventService.generateQRCodeForEvent(participation.getEvent());
            helper.addAttachment("QRCode.png", new ByteArrayResource(qrCodeBytes), "image/png");

            // Définir le sujet du mail
            helper.setSubject("Confirmation de participation à l'événement");

            // Définir l'adresse e-mail du destinataire
            helper.setTo(participation.getEmail());

            // Envoyer le mail
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Gérer les erreurs d'envoi de mail
        }
    }
    /*
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
     */

}
