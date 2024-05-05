package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Commentaire;
import com.example.virtiverse.entities.PubItem;
import com.example.virtiverse.repository.CommentaireRepository;
import com.example.virtiverse.repository.PubItemRepository;
import com.example.virtiverse.serviceInterface.ICommentaireService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentaireServiceImp implements ICommentaireService {

    PubItemRepository pubItemRepository;
    CommentaireRepository commentaireRepository;
    @Override
    public Commentaire addCommentaireToPub(Commentaire commentaire, Long id_pub) {
        PubItem pubItem = pubItemRepository.findById(id_pub).orElse(null);
        commentaire.setPubItem(pubItem);
        return commentaireRepository.save(commentaire);
    }

    @Override
    public void deleteCommentaire(Long id_commentaire) {
        commentaireRepository.deleteById(id_commentaire);
    }

    @Override
    public List<Commentaire> getCommentaire() {
        return commentaireRepository.findAll();
    }

}
