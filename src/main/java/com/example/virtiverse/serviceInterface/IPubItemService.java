package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Etat;
import com.example.virtiverse.entities.PubItem;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IPubItemService {



    PubItem addPubitem (PubItem pubItem , MultipartFile multipartFile , Long id) throws IOException;

    List<PubItem> getPubitem ();
    void deletePubitem (Long id_pub);

    List<PubItem> searchPubItems(String keyword);

    

    List<PubItem> filterByPriceRange(float minPrice, float maxPrice);

    List<PubItem> getPubItemsSortedByEtatAsc();

    List<PubItem> getPubItemsSortedByEtatDesc();

    PubItem addPubitemm(@Valid PubItem pubItem, Long id);

    Optional<PubItem> getPubItemById(Long id_pub);


    List<PubItem> getItemsSortedByEtat(Etat etat);

    List<PubItem> getPubItemsByUserId(Long id);

    List<PubItem> getPubItemsSortedByPriceAS();

    List<PubItem> getPubItemsSortedByPriceDS();


    PubItem updatePubitem(PubItem updatedPubItem);

    List<PubItem> getPubItemsByPrice(float price);

    /// PubItem updatePubItem(PubItem updatedPubItem, MultipartFile imageFile) throws IOException;
}
