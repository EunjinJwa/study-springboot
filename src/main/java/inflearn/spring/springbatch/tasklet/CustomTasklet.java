package inflearn.spring.springbatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        String stepName = stepContribution.getStepExecution().getStepName();
        System.out.println(">>> " + stepName + " was executed.");
        return RepeatStatus.FINISHED;
    }
}
