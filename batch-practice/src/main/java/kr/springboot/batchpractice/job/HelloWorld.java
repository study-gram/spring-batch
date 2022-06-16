package kr.springboot.batchpractice.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class HelloWorld {

    /**
     * 이렇게 구성하면 스프링 부트는 항상 배치 스키마를 생성하려고 시도한다.
     * 만약 이미 스키마가 존재한다면 이 시도가 실패하는데, 그렇다 하더라도 기본 설정에 따라 이 실패는 무시되며 이후 과정이 문제 없이 진행된다.
     */

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("basicJob")
                .start(step1())
                .build();
    }

    @Bean
    private Step step1() {
        return this.stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Hello, World");
                    return RepeatStatus.FINISHED;
                }).build();

    }

}
