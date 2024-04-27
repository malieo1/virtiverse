package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.PubItem;

import javax.validation.Valid;
import java.util.List;

public interface IPubItemService {

    PubItem addPubitem (PubItem pubItem);
    PubItem updatePubitem ( PubItem pubItem);
    List<PubItem> getPubitem ();
    void deletePubitem (Long id_pub);

    List<PubItem> searchPubItems(String keyword);

    List<PubItem> getPubItemsSortedByPrice();

    List<PubItem> filterByPriceRange(float minPrice, float maxPrice);

    List<PubItem> getPubItemsSortedByEtatAsc();

    List<PubItem> getPubItemsSortedByEtatDesc();

    PubItem addPubitemm(@Valid PubItem pubItem, Integer id);
}
