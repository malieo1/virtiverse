package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Participation;
import com.example.virtiverse.serviceInterface.IEmailEventService;
import com.example.virtiverse.serviceInterface.IEventService;
import com.example.virtiverse.serviceInterface.IParticipationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class ServiceEmailEvent implements IEmailEventService {
    private final IEventService iEventService;
    private final IParticipationService iParticipationService;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendConfirmationEmailWithQRCode(Participation participation) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Calculer le total du prix de la participation
            double totalPrix = participation.getEvent().getPrixEvent() * participation.getNbPlace();

            // Créer le contenu du mail de confirmation avec le QR code
            String mailContent = "<html>" +
                    "<body style='font-family: Arial, sans-serif;'>" +
                    "<h2>Merci pour votre participation à l'événement \"" + participation.getEvent().getNomEvent() + "\".</h2>" +
                    "<p>Votre participation a été enregistrée avec succès. Voici les détails :</p>" +
                    "<ul>" +
                    "<li><strong>Nom de l'événement :</strong> " + participation.getEvent().getNomEvent() + "</li>" +
                    "<li><strong>Date de début :</strong> " + participation.getEvent().getDateDebutEvent() + "</li>" +
                    "<li><strong>Date de fin :</strong> " + participation.getEvent().getDateFinEvent() + "</li>" +
                    "<li><strong>Nombre de places réservées :</strong> " + participation.getNbPlace() + "</li>" +
                    "<li><strong>Lieu :</strong> " + participation.getEvent().getLieuEvent() + "</li>" +
                    // Ajoutez d'autres détails de l'événement ici
                    "</ul>" +
                    "<p>Le total à payer pour votre participation le jour J est : " + totalPrix + " Dinars</p>" +
                    "<p>Merci encore pour votre participation !</p>" +
                    "</body>" +
                    "</html>";

            // Ajouter le contenu du mail
            helper.setText(mailContent, true);

            // Ajouter le QR code en pièce jointe
            byte[] qrCodeBytes = iParticipationService.generateQRCodeForParticipation(participation);
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

}
