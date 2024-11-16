package com.example.twinguard.Report.repository;

import com.example.twinguard.Report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByReportId(Long id);
    List<Report> findByType(String type);
}
