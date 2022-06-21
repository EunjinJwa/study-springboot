package inflearn.spring.springbatch.test.job;

import inflearn.spring.springbatch.test.tasklet.ExecutionTasklet1;
import inflearn.spring.springbatch.test.tasklet.ExecutionTasklet2;
import inflearn.spring.springbatch.test.tasklet.ExecutionTasklet3;
import inflearn.spring.springbatch.test.tasklet.ExecutionTasklet4;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;

//@Configuration
@RequiredArgsConstructor
public class ExecutionContextConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ExecutionTasklet1 executionTasklet1;
    private final ExecutionTasklet2 executionTasklet2;
    private final ExecutionTasklet3 executionTasklet3;
    private final ExecutionTasklet4 executionTasklet4;
    private final JobExecutionListener jobExecutionListener;

//    @Bean
    public Job excutionContextJob() {
        return jobBuilderFactory.get("executionContextJob")
                .start(step1())
                .next(step2())
                .next(step3())
                .next(step4())
                .listener(jobExecutionListener)
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(executionTasklet1)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(executionTasklet2)
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet(executionTasklet3)
                .build();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet(executionTasklet4)
                .build();
    }
}
