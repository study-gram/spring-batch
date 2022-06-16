package kr.springboot.batchpractice.job;

import kr.springboot.batchpractice.valid.ParameterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
                .validator(validator())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1")
                .tasklet(helloWorldTasklet(null, null)).build();

    }

    /**
     * JobParameters에 접근하기
     * @return
     */
    @StepScope
    @Bean
    public Tasklet helloWorldTasklet(
            @Value("#{jobParameters['name']}") String name,
            @Value("#{jobParameters['fileName']}") String fileName) {

        return (contribution, chunkContext) -> {
            log.info("{}", String.format("Hello, %s", name));
            log.info("{}", String.format("fileName = %s", fileName));
            return RepeatStatus.FINISHED;
        };

    }

//    @Bean
//    public JobParametersValidator validator() {
//
//        DefaultJobParametersValidator validator = new DefaultJobParametersValidator();
//
//        validator.setRequiredKeys(new String[]{"fileName"});
//        validator.setOptionalKeys(new String[]{"name"});
//
//        return validator;
//
//    }

    @Bean
    public CompositeJobParametersValidator validator() {

        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();

        DefaultJobParametersValidator defaultJobParametersValidator = new DefaultJobParametersValidator(
                new String[]{"fileName"},
                new String[]{"name"}
        );

        defaultJobParametersValidator.afterPropertiesSet();

        validator.setValidators(
                Arrays.asList(new ParameterValidator(), defaultJobParametersValidator)
        );

        return validator;

    }

}
