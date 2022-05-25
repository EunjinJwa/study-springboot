package inflearn.spring.springbatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class HelloTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        ExecutionContext executionContext = stepContribution.getStepExecution().getExecutionContext();
        String stepName = stepContribution.getStepExecution().getStepName();

        Long stepId = new Long(0);
        if (executionContext.containsKey("step.id")) {
            stepId = executionContext.getLong("step.id");
        }
        executionContext.putLong("step.id", stepId + 1);
        System.out.println("execute step : " + stepName + " (step.id) : " + executionContext.getLong("step.id"));

        if (stepId < 3) {
            return RepeatStatus.CONTINUABLE;
        }

        return RepeatStatus.FINISHED;
    }
}
