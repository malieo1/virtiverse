package com.example.virtiverse.serviceImp;


import com.example.virtiverse.entities.LikeItem;
import com.example.virtiverse.repository.LikeItemRepository;
import com.example.virtiverse.serviceInterface.ILikeItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeItemServiceImp implements ILikeItemService {
    LikeItemRepository likeItemRepository;

    @Override
    public LikeItem createLike(LikeItem likeItem) {
        return null;
    }

    @Override
    public LikeItem createDislike(LikeItem likeItem) {
        return null;
    }

    @Override
    public LikeItem addLikeToPub(LikeItem likeItem, Long id_pub) {
        return null;
    }
}
