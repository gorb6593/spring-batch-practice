package com.harry.springbatchpractice.domain.entity;

import com.harry.springbatchpractice.domain.entity.enums.JobStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Table(name = "batch_job_history")
@Getter
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class BatchJobHistory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String jobName;

    @Column(nullable = false)
    private String jobParameter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public BatchJobHistory(String jobName, String jobParameter) {
        this.jobName = jobName;
        this.jobParameter = jobParameter;
        this.status = JobStatus.STARTED;
        this.startTime = LocalDateTime.now();
    }

    public void complete() {
        this.status = JobStatus.COMPLETED;
        this.endTime = LocalDateTime.now();
    }

    public void fail() {
        this.status = JobStatus.FAILED;
        this.endTime = LocalDateTime.now();
    }
}
