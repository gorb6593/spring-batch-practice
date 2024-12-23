package com.harry.springbatchpractice.domain.repository;

import com.harry.springbatchpractice.domain.entity.BatchJobHistory;
import com.harry.springbatchpractice.domain.entity.QBatchJobHistory;
import com.harry.springbatchpractice.domain.entity.enums.JobStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BatchJobHistoryQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<BatchJobHistory> findRecentJobHistories(String jobName, int limit) {
        return queryFactory
                .selectFrom(QBatchJobHistory.batchJobHistory)
                .where(QBatchJobHistory.batchJobHistory.jobName.eq(jobName))
                .orderBy(QBatchJobHistory.batchJobHistory.createdAt.desc())
                .limit(limit)
                .fetch();
    }

    public List<BatchJobHistory> findFailedJobsInPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return queryFactory
                .selectFrom(QBatchJobHistory.batchJobHistory)
                .where(QBatchJobHistory.batchJobHistory.status.eq(JobStatus.FAILED)
                        .and(QBatchJobHistory.batchJobHistory.createdAt.between(startDate, endDate)))
                .orderBy(QBatchJobHistory.batchJobHistory.createdAt.desc())
                .fetch();
    }
}
