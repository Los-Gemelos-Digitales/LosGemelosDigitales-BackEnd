package com.example.twinguard.Report.controller;

import com.example.twinguard.Report.entity.Report;
import com.example.twinguard.Report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/reports")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Obtener todos los reports
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getReports();
        return ResponseEntity.ok(reports);
    }

    // Obtener un report por ID
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        Optional<Report> report = reportService.getReportById(id);
        if (report.isPresent()) {
            return ResponseEntity.ok(report.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener reports por tipo
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Report>> getReportsByType(@PathVariable String type) {
        List<Report> reports = reportService.getReportsByType(type);
        return ResponseEntity.ok(reports);
    }

    // Crear un nuevo report
    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        Report savedReport = reportService.saveReport(report);
        return ResponseEntity.ok(savedReport);
    }

    // Actualizar un report existente
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report updatedReport) {
        Optional<Report> existingReport = reportService.getReportById(id);
        if (existingReport.isPresent()) {
            Report reportToUpdate = existingReport.get();
            reportToUpdate.setAdminId(updatedReport.getAdminId());
            reportToUpdate.setType(updatedReport.getType());
            reportToUpdate.setDescription(updatedReport.getDescription());
            reportToUpdate.setDetail(updatedReport.getDetail());
            reportToUpdate.setDate(updatedReport.getDate());
            reportToUpdate.setPlace(updatedReport.getPlace());
            Report savedReport = reportService.saveReport(reportToUpdate);
            return ResponseEntity.ok(savedReport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un report por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteReport(@PathVariable Long id) {
        Optional<Report> existingReport = reportService.getReportById(id);
        if (existingReport.isPresent()) {
            reportService.deleteReportById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Report deleted successfully.");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
