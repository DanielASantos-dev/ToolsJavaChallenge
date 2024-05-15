package org.example.infrastructure.repository;

import org.example.core.domain.enums.StatusEnum;
import org.example.infrastructure.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    Optional<TransactionEntity> findByIdAndStatus(Long id, StatusEnum status);

    @Query("SELECT t FROM TransactionEntity t WHERE t.status = 'AUTORIZADO'")
    List<TransactionEntity> findAll();
}
