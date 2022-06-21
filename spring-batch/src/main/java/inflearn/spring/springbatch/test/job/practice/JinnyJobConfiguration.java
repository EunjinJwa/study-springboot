package inflearn.spring.springbatch.test.job.practice;

import inflearn.spring.springbatch.test.jobConfig.StepListener;
import inflearn.spring.springbatch.test.tasklet.HelloTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JinnyJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jinnyJob() {
        return jobBuilderFactory.get("jinnyJob")
                .start(jinnyStep1())
                .next(jinnyStep2())
//                .incrementer(new RunIdIncrementer())
                .build();

    }

    @Bean
    public Step jinnyStep1() {
        return stepBuilderFactory.get("jinnyStep1")
                .tasklet(new HelloTasklet())
                .listener(new StepListener())
                .startLimit(5)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step jinnyStep2() {
        return stepBuilderFactory.get("jinnyStep2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("execute step : " + stepContribution.getStepExecution().getStepName());
//                    throw new RuntimeException("step 2 Exception");
                    return RepeatStatus.FINISHED;
                })
                .listener(new StepListener())
                .allowStartIfComplete(false)
                .build();
    }


}
