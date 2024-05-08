package com.example.virtiverse.serviceImp;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.example.virtiverse.entities.Event;
import com.example.virtiverse.entities.LostandFound;
import com.example.virtiverse.entities.Messages;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.LostandFoundRepository;
import com.example.virtiverse.repository.MessageRepository;
import com.example.virtiverse.repository.OurUserRepo;
import com.example.virtiverse.serviceInterface.LostandFoundInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LostabdFoundService implements LostandFoundInterface {

    private static final String
            containerName = "wadhah";
    // Définir la chaîne de connexion
    private static final String connectionString = "DefaultEndpointsProtocol=https;AccountName=wadhahdaoud;AccountKey=axmwySmbufN/2Z6vfigEXhcvCih9gf2rp4YNSpJTRfpsMKyY4OLZGuMPanDfSV8U4deBziC2AaIN+AStYZYACw==;EndpointSuffix=core.windows.net";

    LostandFoundRepository lostandFoundRepository;
    MessageRepository messageRepository;
    OurUserRepo userrepo;

    @Override
    public List<LostandFound> retrievebyName(String name) {
        return lostandFoundRepository.findByNameOrderByDatePub(name);
    }

    @Override
    public LostandFound changestatus(long idpub) {
        LostandFound publication = lostandFoundRepository.findById(idpub).orElse(null);
        publication.setStatus("Abolished");
        return lostandFoundRepository.save(publication);



    }

    @Override
    public List<LostandFound> retrievebyStatus(String status) {
        return lostandFoundRepository.findByStatusOrderByDatePub(status);
    }

    @Override
    public List<LostandFound> retrievebyUser(long id) {
        return lostandFoundRepository.findByIduserId(id);
    }

    @Override
    public List<LostandFound> retrieveAllPubs() {
        return lostandFoundRepository.findAll();
    }

    @Override
    public LostandFound addPub(LostandFound pub , MultipartFile file , long id ) {
        try {
            // Uploader le fichier dans Azure Blob Storage
            String imageUrl = uploadToAzureBlobStorage(file);
            pub.setImage(imageUrl);
        } catch (IOException e) {
            // Gérer les erreurs d'entrée/sortie
            e.printStackTrace();
            throw new RuntimeException("Erreur lors du téléchargement de l'image", e);
        }
        User user = userrepo.findById(id).orElse(null);
        pub.setIduser(user);

        return lostandFoundRepository.save(pub);
    }
    private String uploadToAzureBlobStorage(MultipartFile file) throws IOException {

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();

        System.out.println("connection reussite");
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

        System.out.println("container found");
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        // Uploader le fichier dans le conteneur Azure Blob Storage
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        blobClient.upload(file.getInputStream(), file.getSize());

        // Retournez l'URL de l'image dans Azure Blob Storage
        return blobClient.getBlobUrl();
    }

    @Override
    public LostandFound updatePub( LostandFound updatedpub) {
        LostandFound existingpub = lostandFoundRepository.findById(updatedpub.getIdPub())
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + updatedpub.getIdPub()));
        // Mettre à jour les propriétés de l'événement existant avec les nouvelles valeurs
        existingpub.setName(updatedpub.getName());
        existingpub.setDescription(updatedpub.getDescription());
        existingpub.setLocation(updatedpub.getLocation());
        existingpub.setDatePub(updatedpub.getDatePub());
        existingpub.setStatus(updatedpub.getStatus());
        // Enregistrer les modifications dans la base de données et retourner l'événement mis à jour
        return lostandFoundRepository.save(existingpub);
    }

    @Override
    public void removePub(Long idpub) {
    lostandFoundRepository.deleteById(idpub);

    }

    @Override
    public LostandFound retrievepub(Long idpub) {
        return lostandFoundRepository.findById(idpub).orElse(null);
    }


    @Override
    public void removePublicationWithMessages(long idPub) {
        LostandFound publication = lostandFoundRepository.findById(idPub).orElse(null);
        if (publication != null) {
            // Récupérer tous les messages associés à la publication
            List<Messages> messages = messageRepository.findByIdpubIdPubOrderBySendat(idPub);

            // Supprimer chaque message individuellement
            messageRepository.deleteAll(messages);

            // Enfin, supprimer la publication
            lostandFoundRepository.delete(publication);
        }
    }
}
