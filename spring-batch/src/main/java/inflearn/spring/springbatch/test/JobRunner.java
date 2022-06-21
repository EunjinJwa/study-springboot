package inflearn.spring.springbatch.test;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobRunner implements ApplicationRunner {

    @Autowired
    private JobLauncher jobLauncher;    // Job을 실행시킬 수 있는 런쳐

    @Autowired
    private Job welcomeJob;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("requestDate", "20220515")
//                .toJobParameters();
//
//        jobLauncher.run(welcomeJob, jobParameters);
    }
}
