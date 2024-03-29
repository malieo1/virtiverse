package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Commentaire;
import com.example.virtiverse.serviceInterface.ICommentaireService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentaire")
@AllArgsConstructor
public class CommentaireController {

    ICommentaireService iCommentaireService;


    @GetMapping("/retrieveAllCommentaire")
    public List<Commentaire> getCommentaire() {
        return iCommentaireService.getCommentaire();
    }


    @PostMapping("/addCommentaire/{id_pub}")
    public Commentaire addCommentaireToPub(@RequestBody Commentaire commentaire,@PathVariable("id_pub") long id_pub) {
        return iCommentaireService.addCommentaireToPub(commentaire, id_pub);
    }

    @DeleteMapping("/removeCommentaire/{id}")
    public void deleteCommentaire(@PathVariable("id") Long id_commentaire) {
        iCommentaireService.deleteCommentaire(id_commentaire);
    }
}

