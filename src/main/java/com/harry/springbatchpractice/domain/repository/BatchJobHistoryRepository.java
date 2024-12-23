package com.harry.springbatchpractice.domain.repository;

import com.harry.springbatchpractice.domain.entity.BatchJobHistory;
import com.harry.springbatchpractice.domain.entity.enums.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BatchJobHistoryRepository extends JpaRepository<BatchJobHistory, Long> {
    List<BatchJobHistory> findByJobNameOrderByCreatedAtDesc(String jobName);

    Optional<BatchJobHistory> findTopByJobNameOrderByCreatedAtDesc(String jobName);

    @Query("SELECT bj FROM BatchJobHistory bj WHERE bj.status = :status " +
            "AND bj.createdAt BETWEEN :startDate AND :endDate")
    List<BatchJobHistory> findByStatusAndDateBetween(
            @Param("status") JobStatus status,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}

