package com.example.twinguard.Report.service;

import com.example.twinguard.Report.entity.Report;
import com.example.twinguard.Report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;
    public List<Report> getReports(){
        return reportRepository.findAll();
    }
    public Optional<Report> getReportById(Long id) {
        return reportRepository.findByReportId(id);
    }

    public List<Report> getReportsByType(String type) {
        return reportRepository.findByType(type);
    }

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    public void deleteReportById(Long id) {
        reportRepository.deleteById(id);
    }
}
