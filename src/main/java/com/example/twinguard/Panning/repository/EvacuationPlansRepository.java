package com.example.twinguard.Panning.repository;

import com.example.twinguard.Panning.entity.EvacuationPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvacuationPlansRepository extends JpaRepository<EvacuationPlans, Long> {
}
