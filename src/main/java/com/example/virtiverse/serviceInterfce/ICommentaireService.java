package com.example.virtiverse.serviceInterfce;

import com.example.virtiverse.entities.Commentaire;
import com.example.virtiverse.entities.PubItem;

import java.util.List;

public interface ICommentaireService {

    Commentaire addCommentaireToPub (Commentaire commentaire, Long id_pub);
    void deleteCommentaire (Long id_commentaire);

    List<Commentaire> getCommentaire ();
}
