package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Avis;
import com.example.virtiverse.entities.Covoiturage;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.AvisRepository;
import com.example.virtiverse.repository.CovoiturageRepository;
import com.example.virtiverse.repository.OurUserRepo;
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
    OurUserRepo ourUserRepo;
    @Override
    public List<Covoiturage> retreiveCovoiturages() {
        return covoiturageRepository.findAll();
    }

    @Override
    public Covoiturage AddCovoiturage(Covoiturage covoiturage,Long iduser) {
        Optional<User> optionalUser = ourUserRepo.findById(iduser);
        User user = optionalUser.get();
        covoiturage.setUserid(user);
        return covoiturageRepository.save(covoiturage);
    }

    @Override
    public Covoiturage updateCovoiturage(Long id_cov,Covoiturage covoiturage2) {
       Optional<Covoiturage> covoiturage1 = covoiturageRepository.findById(id_cov);
      Covoiturage OldCovoiturage = covoiturage1.get();
      OldCovoiturage.setNombre_placecov(covoiturage2.getNombre_placecov());
        OldCovoiturage.setDate_depart(covoiturage2.getDate_depart());
        OldCovoiturage.setDescription(covoiturage2.getDescription());
        OldCovoiturage.setLieu_depart(covoiturage2.getLieu_depart());
        OldCovoiturage.setDestination(covoiturage2.getDestination());
        return covoiturageRepository.save(OldCovoiturage);


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

    @Override
    public List<Covoiturage> retrieveAllCovByUser(Long id) {
        return covoiturageRepository.findByUseridId(id);
    }

    @Override
    public List<Covoiturage> searchCovByDest(String destination) {
        return covoiturageRepository.findByDestinationContainingIgnoreCase(destination);
    }

    @Override
    public void ReserveCov(Long id_cov, Long idUser) {
        Covoiturage covoiturage = covoiturageRepository.findById(id_cov).orElse(null);
        int PlacesRestantes = covoiturage.getNombre_placecov() -1 ;

        Optional<User> OptionalUser = ourUserRepo.findById(idUser);
        User user = OptionalUser.get();
        covoiturage.getReservations().add(user);
        covoiturage.setNombre_placecov(PlacesRestantes);
        covoiturageRepository.save(covoiturage);
    }


}
