package inflearn.spring.springbatch.test.job.flow;

import inflearn.spring.springbatch.test.tasklet.TaskletStepTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FlowJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TaskletStepTemplate taskletStepTemplate;

    @Bean
    public Job flowJob() {
        return jobBuilderFactory.get("flowJob")
                .start(flowA())
                .next(taskletStepTemplate.getStep("step3"))
                .next(flowB())
                .end()
                .build();
    }

    @Bean
    public Flow flowA() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowA");
        flowBuilder.start(taskletStepTemplate.getStep("step1"))
                .next(taskletStepTemplate.getStep("step2"))
                .end();
        return flowBuilder.build();
    }

    @Bean
    public Flow flowB() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowB");
        flowBuilder.start(taskletStepTemplate.getStep("step4"))
                .next(taskletStepTemplate.getStep("step5"))
                .end();
        return flowBuilder.build();
    }
}
