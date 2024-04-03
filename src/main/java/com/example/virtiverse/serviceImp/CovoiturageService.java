package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Covoiturage;
import com.example.virtiverse.repository.CovoiturageRepository;
import com.example.virtiverse.serviceInterface.CovoiturageInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CovoiturageService implements CovoiturageInterface {
    CovoiturageRepository covoiturageRepository;
    @Override
    public List<Covoiturage> retreiveCovoiturages() {
        return covoiturageRepository.findAll();
    }

    @Override
    public Covoiturage AddCovoiturage(Covoiturage covoiturage) {
        return covoiturageRepository.save(covoiturage);
    }

    @Override
    public Covoiturage updateCovoiturage(Covoiturage covoiturage) {
        return covoiturageRepository.save(covoiturage);
    }

    @Override
    public void removeCovoiturage(Long id_cov) {
        covoiturageRepository.deleteById(id_cov);
    }

    @Override
    public Covoiturage retreiveCov(Long id_cov) {
        return covoiturageRepository.findById(id_cov).orElse(null);
    }
}
