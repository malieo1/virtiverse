package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Covoiturage;
import com.example.virtiverse.entities.Event;

import java.util.List;

public interface CovoiturageInterface {
    List<Covoiturage> retreiveCovoiturages();
    Covoiturage AddCovoiturage(Covoiturage covoiturage,Long iduser);
    Covoiturage updateCovoiturage (Long id_cov,Covoiturage covoiturage);
    void removeCovoiturage (Long id_cov);
    Covoiturage retreiveCov(Long id_cov);
     void updateNombrePlacecov(Long covId, int newNombrePlacecov);
    List<Covoiturage> retrieveAllCovByUser(Long id);
    List<Covoiturage> searchCovByDest(String destination);
    void ReserveCov (Long id_cov,Long idUser);

}
