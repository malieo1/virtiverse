package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.LostandFound;
import com.example.virtiverse.repository.LostandFoundRepository;
import com.example.virtiverse.serviceInterface.LostandFoundInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LostabdFoundService implements LostandFoundInterface {
    LostandFoundRepository lostandFoundRepository;
    @Override
    public List<LostandFound> retrieveAllPubs() {
        return lostandFoundRepository.findAll();
    }

    @Override
    public LostandFound addPub(LostandFound pub) {
        return lostandFoundRepository.save(pub);
    }

    @Override
    public LostandFound updatePub(LostandFound pub) {
        return lostandFoundRepository.save(pub);
    }

    @Override
    public void removePub(Long idpub) {
    lostandFoundRepository.deleteById(idpub);

    }

    @Override
    public LostandFound retrievepub(Long idpub) {
        return lostandFoundRepository.findById(idpub).orElse(null);
    }
}
