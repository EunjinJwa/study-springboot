package inflearn.spring.springbatch.test.job;

import inflearn.spring.springbatch.test.jobConfig.CustomJobParametersValidator;
import inflearn.spring.springbatch.test.tasklet.HelloTasklet;
import inflearn.spring.springbatch.test.tasklet.CustomTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .start(helloStep1())
                .next(helloStep2())
                .validator(new CustomJobParametersValidator())      // custom하게 validation
//                .validator(new DefaultJobParametersValidator(new String[]{"name", "date"}, new String[]{"run.id"}))  // 필수/옵션 key 정의
                .preventRestart()
//                .incrementer(new CustomJobParametersIncrementer())      // custom하게 증가되는 JobParameter 설정
                .incrementer(new RunIdIncrementer())    // 자동으로 1씩 증가한s JobParameter
                .build();
    }

    @Bean
    public Job helloJob2() {
        return jobBuilderFactory.get("helloJob2")
                .start(flow())
                .next(helloStep5())
                .end()  // flow builder는 end()가 필요함
                .build();
    }

    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get("helloStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(" ============================ ");
                        System.out.println(" >> Hello Spring Batch !! ");
                        System.out.println(" ============================ ");

                        // JobParameter 확인 방법
                        JobParameters jobParameters = stepContribution.getStepExecution().getJobExecution().getJobParameters();
                        jobParameters.getString("name");
//                        jobParameters.getDate("date");

                        Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();

                        return RepeatStatus.FINISHED;   // tasklet이 한 번만 실행되고 끝남.
                        // tasklet 은 기본적으로는 무한 반복임. 반복 여부 설정을 위한 RepeatStatus 타입 설정을 해줘야 함.
                    }
                })
                .build();
    }

    @Bean
    public Step helloStep2() {
        return stepBuilderFactory.get("helloStep2")
                .tasklet(new CustomTasklet())
                .build();
    }

    @Bean
    public Flow flow() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder("flow");
        flowBuilder.start(helloStep3())
                .next(helloStep4())
                .end();
        return flowBuilder.build();
    }

    @Bean
    public Step helloStep3() {
        return stepBuilderFactory.get("helloStep3")
                .tasklet(new HelloTasklet())
                .build();
    }

    @Bean
    public Step helloStep4() {
        return stepBuilderFactory.get("helloStep4")
                .tasklet(new HelloTasklet())
                .build();
    }


    @Bean
    public Step helloStep5() {
        return stepBuilderFactory.get("helloStep5")
                .tasklet(new HelloTasklet())
                .build();
    }
}
