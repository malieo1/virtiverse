package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Avis;
import com.example.virtiverse.entities.Covoiturage;
import com.example.virtiverse.repository.AvisRepository;
import com.example.virtiverse.repository.CovoiturageRepository;
import com.example.virtiverse.serviceInterface.AvisInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AvisService implements AvisInterface {
    AvisRepository avisRepository;
    CovoiturageRepository covoiturageRepository;
    @Override
    public List<Avis> retreiveAvis() {
        return avisRepository.findAll();
    }

    @Override
    public Avis AddAvis(Avis avis, Long id_cov) {
        Covoiturage cov = covoiturageRepository.findById(id_cov).orElse(null);
        avis.setCovoiturage(cov);
        avis.setDate_avis(LocalDate.now());
        return avisRepository.save(avis);
    }

    @Override
    public Avis updateAvis(Avis avis) {
        return avisRepository.save(avis);
    }

    @Override
    public void removeAvis(Long id_avis) {
        avisRepository.deleteById(id_avis);

    }

    @Override
    public Avis retreiveAvis(Long id_avis) {
        return avisRepository.findById(id_avis).orElse(null);
    }


    @Override
    public List<Avis> FindByCovoiturage(Long id_cov)
    {
        return avisRepository.findAvisByCovoiturageId(id_cov);
    }
}
