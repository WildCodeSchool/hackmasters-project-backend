package com.templateproject.api.repository;

import com.templateproject.api.entity.RecipeDiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDietRepository extends JpaRepository<RecipeDiet, Long> {
}