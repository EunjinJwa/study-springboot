package inflearn.spring.springbatch.test.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionTasklet4 implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("> tasklet4");

        System.out.println(chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name"));
        return RepeatStatus.FINISHED;
    }
}
