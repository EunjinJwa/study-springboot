package inflearn.spring.springbatch.test.tasklet;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskletStepTemplate {

    private final StepBuilderFactory stepBuilderFactory;

    public Step getStep(String stepName) {
        return stepTemplate(stepName);
    }

//    @Bean
    public Step stepTemplate(String name) {
        return stepBuilderFactory.get(name)
                .tasklet(new CustomTasklet())
                .build();
    }


}
