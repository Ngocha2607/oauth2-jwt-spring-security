package com.springboot.eCommerce.repository;

import com.springboot.eCommerce.entity.InvalidatedToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
    @Modifying
    @Transactional // Ensure this method is part of a transaction
    @Query("DELETE FROM InvalidatedToken t WHERE t.expiryTime <= ?1")
    void deleteAllExpiryTimeToken(Date date);
}
