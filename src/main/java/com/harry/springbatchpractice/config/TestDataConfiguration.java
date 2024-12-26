package com.harry.springbatchpractice.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TestDataConfiguration {

    @Bean
    public Job testJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("testJob", jobRepository)
                .start(testStep(jobRepository, transactionManager))
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step testStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("testStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Test Step 실행");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}
