package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.ContratLocation;
import com.example.virtiverse.entities.Maison;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.ContratLocationRepository;
import com.example.virtiverse.repository.MaisonRepository;
import com.example.virtiverse.repository.OurUserRepo;
import com.example.virtiverse.serviceInterface.IContratLocation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContratLocationService implements IContratLocation {
    ContratLocationRepository contratLocationRepository;
    MaisonRepository maisonRepository;
    OurUserRepo userRepository;
    @Override
    public List<ContratLocation> getAllContratsLocation() {
        return contratLocationRepository.findAll();
    }

    @Override
    public ContratLocation addContratLocation(ContratLocation contratLocation, Long id_maison) {
        Maison maison= maisonRepository.findById(id_maison).orElse(null);
        contratLocation.setM(maison);
        return contratLocationRepository.save(contratLocation);
    }


    @Override
    public ContratLocation updateContratLocation(ContratLocation contratLocation, Long id_maison) {
        Maison maison= maisonRepository.findById(id_maison).orElse(null);
        contratLocation.setM(maison);
        return contratLocationRepository.save(contratLocation);
    }

    @Override
    public ContratLocation getContratLocation(Long id_location) {
        return contratLocationRepository.findById(id_location).orElse(null);
    }

    @Override
    public void deleteContratLocation(Long id_contrat) {
        contratLocationRepository.deleteById(id_contrat);
    }
    @Override
    public ContratLocation addContratByUserAndMaison(ContratLocation contratLocation, Long id, Long id_maison) {
        Optional<User> optionalUser= userRepository.findById(id);
        User user = optionalUser.get();
        Maison maison = maisonRepository.findById(id_maison).orElse(null);
        contratLocation.setM(maison);
        if (contratLocation.getColocataires() == null) {
            contratLocation.setColocataires(new ArrayList<>());
        }
        contratLocation.getColocataires().add(user);
        return contratLocationRepository.save(contratLocation);
    }
    public List<ContratLocation> getContratsByUser(Long id) {
        return contratLocationRepository.findByMUserId(id);
    }



}
