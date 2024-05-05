package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.LostandFound;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LostandFoundInterface {
    List<LostandFound> retrieveAllPubs();
    LostandFound addPub(LostandFound pub , MultipartFile file);
    void removePub (Long idpub);
    LostandFound retrievepub (Long idpub);
    LostandFound updatePub (LostandFound pub);

    List<LostandFound> retrievebyStatus(String status);
    public void removePublicationWithMessages(long idPub);

    List<LostandFound> retrievebyName(String name);


}
