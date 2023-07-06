package com.templateproject.api.repository;

import com.templateproject.api.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepRepository extends JpaRepository<Step, Integer> {

}