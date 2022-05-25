package inflearn.spring.springbatch.jobConfig;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        String stepName = stepExecution.getStepName();
        JobParameters jobParameters = stepExecution.getJobParameters();

        System.out.println("=======================================================");
        System.out.println(String.format("[%s] stepName : %s", jobName, stepName));
        System.out.println("jobParameters : " + jobParameters.getParameters());
        System.out.println("=======================================================");

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("completed step : " + stepExecution.getStepName());
        return ExitStatus.COMPLETED;
    }
}
