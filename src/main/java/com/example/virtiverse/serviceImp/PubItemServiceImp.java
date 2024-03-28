package com.example.virtiverse.serviceImp;


import com.example.virtiverse.entities.PubItem;
import com.example.virtiverse.repository.PubItemRepository;
import com.example.virtiverse.serviceInterfce.IPubItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PubItemServiceImp implements IPubItemService {

    PubItemRepository pubItemRepository;
    @Override
    public PubItem addPubitem(PubItem pubItem) {
        return pubItemRepository.save(pubItem);
    }

    @Override
    public PubItem updatePubitem(PubItem pubItem) {
        return pubItemRepository.save(pubItem);
    }

    @Override
    public List<PubItem> getPubitem() {
        return pubItemRepository.findAll();
    }

    @Override
    public void deletePubitem(Long id_pub) {
        pubItemRepository.deleteById(id_pub);
    }
}
