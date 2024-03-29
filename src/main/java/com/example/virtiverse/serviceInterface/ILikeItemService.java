package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.LikeItem;

public interface ILikeItemService {
    LikeItem createLike(LikeItem likeItem);
    LikeItem createDislike(LikeItem likeItem);

    LikeItem addLikeToPub (LikeItem likeItem, Long id_pub);

}
