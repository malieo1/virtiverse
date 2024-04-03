package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.LostandFound;

import java.util.List;

public interface LostandFoundInterface {
    List<LostandFound> retrieveAllPubs();
    LostandFound addPub(LostandFound pub);
    void removePub (Long idpub);
    LostandFound retrievepub (Long idpub);
    LostandFound updatePub (LostandFound pub);


}
