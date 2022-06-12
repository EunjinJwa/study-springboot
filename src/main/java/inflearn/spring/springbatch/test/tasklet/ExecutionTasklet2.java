package inflearn.spring.springbatch.test.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionTasklet2 implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("> tasklet2");

        ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
        ExecutionContext stepContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();

        System.out.println("jobName : " + jobContext.get("jobName"));
        System.out.println("stepName : " + stepContext.get("stepName"));    // step간에 내용공유 안되므로 앞 step의 name을 여기서는 알 수 없음.

        String stepName = chunkContext.getStepContext().getStepExecution().getStepName();
        if (stepContext.get("stepName") == null) {
            stepContext.put("stepName", stepName);
        }

        return RepeatStatus.FINISHED;
    }
}
