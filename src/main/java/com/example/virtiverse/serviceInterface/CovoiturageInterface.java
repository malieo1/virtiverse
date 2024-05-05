package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Covoiturage;

import java.util.List;

public interface CovoiturageInterface {
    List<Covoiturage> retreiveCovoiturages();
    Covoiturage AddCovoiturage(Covoiturage covoiturage);
    Covoiturage updateCovoiturage (Covoiturage covoiturage);
    void removeCovoiturage (Long id_cov);
    Covoiturage retreiveCov(Long id_cov);
     void updateNombrePlacecov(Long covId, int newNombrePlacecov);
     //
}
