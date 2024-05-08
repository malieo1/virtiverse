package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Avis;
import com.example.virtiverse.entities.Covoiturage;

import java.util.List;

public interface AvisInterface {
    List<Avis> retreiveAvis();
    Avis AddAvis(Avis avis,Long id_cov,Long id);
    Avis updateAvis (Avis avis);
    void removeAvis (Long id_avis);
    Avis retreiveAvis(Long id_avis);

    List<Avis> FindByCovoiturage(Long id_cov);
    List<Avis> retrieveAllAvisByUser(Long id);

}
