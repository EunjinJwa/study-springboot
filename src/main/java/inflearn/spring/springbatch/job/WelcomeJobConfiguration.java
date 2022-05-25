package inflearn.spring.springbatch.job;

import inflearn.spring.springbatch.tasklet.CustomTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class WelcomeJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job welcomeJob() {
        return jobBuilderFactory.get("welcomeJob")
                .start(welcomeStep1())
                .next(welcomeStep2())
                .build();
    }

    @Bean
    public Step welcomeStep1() {
        return stepBuilderFactory.get("welcomeStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        System.out.println(" ============================ ");
                        System.out.println(" >> Welcome Spring Batch !! ");
                        System.out.println(" ============================ ");

                        return RepeatStatus.FINISHED;   // tasklet이 한 번만 실행되고 끝남.
                        // tasklet 은 기본적으로는 무한 반복임. 반복 여부 설정을 위한 RepeatStatus 타입 설정을 해줘야 함.
                    }
                })
                .build();
    }

    @Bean
    public Step welcomeStep2() {
        return stepBuilderFactory.get("welcomeStep2")
                .tasklet(new CustomTasklet())
                .build();
    }
}
