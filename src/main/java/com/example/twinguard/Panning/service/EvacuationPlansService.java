package com.example.twinguard.Panning.service;

import com.example.twinguard.Panning.entity.EvacuationPlans;
import com.example.twinguard.Panning.repository.EvacuationPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvacuationPlansService {

    @Autowired
    private EvacuationPlansRepository evacuationPlansRepository;

    // Obtener todos los planes de evacuación
    public List<EvacuationPlans> getEvacuationPlans() {
        return evacuationPlansRepository.findAll();
    }

    // Obtener un plan de evacuación por ID
    public Optional<EvacuationPlans> getEvacuationPlanById(Long id) {
        return evacuationPlansRepository.findById(id);
    }

    // Crear un nuevo plan de evacuación
    public EvacuationPlans saveEvacuationPlan(EvacuationPlans evacuationPlan) {
        return evacuationPlansRepository.save(evacuationPlan);
    }

    // Eliminar un plan de evacuación por ID
    public void deleteEvacuationPlanById(Long id) {
        evacuationPlansRepository.deleteById(id);
    }
}
