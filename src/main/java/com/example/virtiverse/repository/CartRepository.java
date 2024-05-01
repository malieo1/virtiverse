package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Cart;
import com.example.virtiverse.entities.PubItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c " +
            "WHERE (:cartId IS NULL OR c.id = :cartId) " +
            "AND (:total IS NULL OR c.total = :total) " +
            "AND (:itemName IS NULL OR EXISTS (SELECT p FROM c.pubItems p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :itemName, '%'))))")
    List<Cart> findByCriteria(@Param("cartId") Long cartId,
                              @Param("total") BigDecimal total,
                              @Param("itemName") String itemName);
}

