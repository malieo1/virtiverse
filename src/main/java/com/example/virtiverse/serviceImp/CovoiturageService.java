package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Avis;
import com.example.virtiverse.entities.Covoiturage;
import com.example.virtiverse.repository.AvisRepository;
import com.example.virtiverse.repository.CovoiturageRepository;
import com.example.virtiverse.serviceInterface.CovoiturageInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CovoiturageService implements CovoiturageInterface {
    CovoiturageRepository covoiturageRepository;
    AvisRepository avisRepository;
    @Override
    public List<Covoiturage> retreiveCovoiturages() {
        return covoiturageRepository.findAll();
    }

    @Override
    public Covoiturage AddCovoiturage(Covoiturage covoiturage) {
        covoiturage.setDate_depart(LocalDate.now());
        return covoiturageRepository.save(covoiturage);
    }

    @Override
    public Covoiturage updateCovoiturage(Covoiturage covoiturage) {
        return covoiturageRepository.save(covoiturage);
    }

    @Override
    public void removeCovoiturage(Long id_cov) {
       // covoiturageRepository.deleteById(id_cov);
        Covoiturage covoiturage = covoiturageRepository.findById(id_cov).orElse(null);
        if (covoiturage != null){
            List <Avis> ListAvisCov = avisRepository.findAvisByCovoiturageId(id_cov);
            avisRepository.deleteAll(ListAvisCov);
            covoiturageRepository.delete(covoiturage);
        }

    }

    @Override
    public Covoiturage retreiveCov(Long id_cov) {
        return covoiturageRepository.findById(id_cov).orElse(null);
    }
    public void updateNombrePlacecov(Long covId, int newNombrePlacecov) {
        Optional<Covoiturage> optionalCovoiturage = covoiturageRepository.findById(covId);
        if (optionalCovoiturage.isPresent()) {
            Covoiturage covoiturage = optionalCovoiturage.get();
            covoiturage.setNombre_placecov(newNombrePlacecov);
            covoiturageRepository.save(covoiturage);
        } else {
            throw new RuntimeException("Covoiturage not found with id: " + covId);
        }
    }
    /*

    */
}
