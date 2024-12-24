package com.harry.springbatchpractice.domain.service;

import com.harry.springbatchpractice.domain.entity.BatchJobHistory;
import com.harry.springbatchpractice.domain.repository.BatchJobHistoryQueryRepository;
import com.harry.springbatchpractice.domain.repository.BatchJobHistoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BatchJobHistoryService {

    private final BatchJobHistoryRepository batchJobHistoryRepository;
    private final BatchJobHistoryQueryRepository batchJobHistoryQueryRepository;

    public BatchJobHistory createJobHistory(String jobName, String jobParameter) {
        BatchJobHistory history = BatchJobHistory.builder()
                .jobName(jobName)
                .jobParameter(jobParameter)
                .build();
        return batchJobHistoryRepository.save(history);
    }

    public void markJobAsCompleted(Long historyId) {
        BatchJobHistory history = batchJobHistoryRepository.findById(historyId)
                .orElseThrow(() -> new IllegalArgumentException("Job history not found: " + historyId));
        history.complete();
    }

    public void markJobAsFailed(Long historyId) {
        BatchJobHistory history = batchJobHistoryRepository.findById(historyId)
                .orElseThrow(() -> new IllegalArgumentException("Job history not found: " + historyId));
        history.fail();
    }

    @Transactional(readOnly = true)
    public List<BatchJobHistory> getRecentJobHistories(String jobName, int limit) {
        return batchJobHistoryQueryRepository.findRecentJobHistories(jobName, limit);
    }

    @Transactional(readOnly = true)
    public List<BatchJobHistory> getFailedJobsInLastDay() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(1);
        return batchJobHistoryQueryRepository.findFailedJobsInPeriod(startDate, endDate);
    }
}
