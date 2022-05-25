package inflearn.spring.springbatch.job.practice;

import inflearn.spring.springbatch.jobConfig.StepListener;
import inflearn.spring.springbatch.tasklet.HelloTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
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
