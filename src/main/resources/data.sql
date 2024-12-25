-- src/test/resources/data.sql

-- BatchJobHistory 테스트 데이터
INSERT INTO batch_job_history (job_name, job_parameter, status, start_time, end_time, created_at, updated_at)
VALUES
    ('sampleJob1', 'param=1', 'COMPLETED', '2024-01-01 10:00:00', '2024-01-01 11:00:00', '2024-01-01 10:00:00', '2024-01-01 11:00:00'),
    ('sampleJob1', 'param=2', 'FAILED', '2024-01-02 10:00:00', '2024-01-02 10:30:00', '2024-01-02 10:00:00', '2024-01-02 10:30:00'),
    ('sampleJob2', 'param=1', 'COMPLETED', '2024-01-03 10:00:00', '2024-01-03 11:00:00', '2024-01-03 10:00:00', '2024-01-03 11:00:00');