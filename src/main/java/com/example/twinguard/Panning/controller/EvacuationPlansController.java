package com.example.twinguard.Panning.controller;

import com.example.twinguard.Panning.entity.EvacuationPlans;
import com.example.twinguard.Panning.service.EvacuationPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/evacuation-plans")
@CrossOrigin(origins = "http://localhost:4200")
public class EvacuationPlansController {

    @Autowired
    private EvacuationPlansService evacuationPlansService;

    // Obtener todos los planes de evacuación
    @GetMapping
    public ResponseEntity<List<EvacuationPlans>> getAllEvacuationPlans() {
        List<EvacuationPlans> evacuationPlans = evacuationPlansService.getEvacuationPlans();
        return ResponseEntity.ok(evacuationPlans);
    }

    // Obtener un plan de evacuación por ID
    @GetMapping("/{id}")
    public ResponseEntity<EvacuationPlans> getEvacuationPlanById(@PathVariable Long id) {
        Optional<EvacuationPlans> evacuationPlan = evacuationPlansService.getEvacuationPlanById(id);
        if (evacuationPlan.isPresent()) {
            return ResponseEntity.ok(evacuationPlan.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo plan de evacuación
    @PostMapping
    public ResponseEntity<EvacuationPlans> createEvacuationPlan(@RequestBody EvacuationPlans evacuationPlan) {
        EvacuationPlans savedEvacuationPlan = evacuationPlansService.saveEvacuationPlan(evacuationPlan);
        return ResponseEntity.ok(savedEvacuationPlan);
    }

    // Eliminar un plan de evacuación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvacuationPlan(@PathVariable Long id) {
        Optional<EvacuationPlans> existingEvacuationPlan = evacuationPlansService.getEvacuationPlanById(id);
        if (existingEvacuationPlan.isPresent()) {
            evacuationPlansService.deleteEvacuationPlanById(id);
            return ResponseEntity.ok().body("Evacuation plan deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
