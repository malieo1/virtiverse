package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Commentaire;

import java.util.List;

public interface ICommentaireService {

    Commentaire addCommentaireToPub (Commentaire commentaire, Long id_pub);
    void deleteCommentaire (Long id_commentaire);

    List<Commentaire> getCommentaire ();
}
