package com.example.virtiverse.serviceImp;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.example.virtiverse.entities.LostandFound;
import com.example.virtiverse.entities.Messages;
import com.example.virtiverse.repository.LostandFoundRepository;
import com.example.virtiverse.repository.MessageRepository;
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

    @Override
    public List<LostandFound> retrievebyName(String name) {
        return lostandFoundRepository.findByNameOrderByDatePub(name);
    }

    @Override
    public List<LostandFound> retrievebyStatus(String status) {
        return lostandFoundRepository.findByStatusOrderByDatePub(status);
    }

    @Override
    public List<LostandFound> retrieveAllPubs() {
        return lostandFoundRepository.findAll();
    }

    @Override
    public LostandFound addPub(LostandFound pub , MultipartFile file) {
        try {
            // Uploader le fichier dans Azure Blob Storage
            String imageUrl = uploadToAzureBlobStorage(file);
            pub.setImage(imageUrl);
        } catch (IOException e) {
            // Gérer les erreurs d'entrée/sortie
            e.printStackTrace();
            throw new RuntimeException("Erreur lors du téléchargement de l'image", e);
        }
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
    public LostandFound updatePub(LostandFound pub) {
        return lostandFoundRepository.save(pub);
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
