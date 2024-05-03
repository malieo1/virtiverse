package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Etat;
import com.example.virtiverse.entities.PubItem;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

public interface IPubItemService {



    PubItem addPubitem (PubItem pubItem , MultipartFile multipartFile , Integer id) throws IOException;
    PubItem updatePubitem ( PubItem pubItem);
    List<PubItem> getPubitem ();
    void deletePubitem (Long id_pub);

    List<PubItem> searchPubItems(String keyword);

    List<PubItem> getPubItemsSortedByPrice();

    List<PubItem> filterByPriceRange(float minPrice, float maxPrice);

    List<PubItem> getPubItemsSortedByEtatAsc();

    List<PubItem> getPubItemsSortedByEtatDesc();

    PubItem addPubitemm(@Valid PubItem pubItem, Integer id);

    PubItem getPubItemById(Long id);


    List<PubItem> getItemsSortedByEtat(Etat etat);
}
