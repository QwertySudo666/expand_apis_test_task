package com.jammy.repository;

import com.jammy.entities.ProductRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductRecordEntity, UUID> {
}
