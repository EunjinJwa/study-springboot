package inflearn.spring.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class HelloTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        String stepName = stepContribution.getStepExecution().getStepName();
        System.out.println("execute step : " + stepName);
        return RepeatStatus.FINISHED;
    }
}
