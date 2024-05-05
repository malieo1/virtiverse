package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.ContratLocation;

import java.util.List;

public interface IContratLocation {
    List<ContratLocation> getAllContratsLocation();
    ContratLocation addContratLocation (ContratLocation contratLocation,Long id_maison);
    ContratLocation updateContratLocation (ContratLocation contratLocation,Long id_maison);
    ContratLocation getContratLocation(Long id_contrat);
    void deleteContratLocation(Long id_contrat);
    ContratLocation addContratByUserAndMaison(ContratLocation contratLocation, Long id, Long id_maison);
}
