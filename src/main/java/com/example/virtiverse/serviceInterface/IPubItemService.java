package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.PubItem;

import java.util.List;

public interface IPubItemService {

    PubItem addPubitem (PubItem pubItem);
    PubItem updatePubitem ( PubItem pubItem);
    List<PubItem> getPubitem ();
    void deletePubitem (Long id_pub);
}
