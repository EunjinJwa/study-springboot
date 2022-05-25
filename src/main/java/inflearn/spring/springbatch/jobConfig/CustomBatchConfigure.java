package inflearn.spring.springbatch.jobConfig;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CustomBatchConfigure extends BasicBatchConfigurer {

    private final DataSource dataSource;

    protected CustomBatchConfigure(BatchProperties properties, DataSource dataSource, TransactionManagerCustomizers transactionManagerCustomizers) {
        super(properties, dataSource, transactionManagerCustomizers);
        this.dataSource = dataSource;
    }

    /**
     * JobRepository 를 Custom하게 만듬
     * @return
     * @throws Exception
     */
    @Override
    protected JobRepository createJobRepository() throws Exception {

        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTransactionManager(getTransactionManager());
        factoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        factoryBean.setTablePrefix("BATCH_"); // default = "BATCH_"
        return factoryBean.getObject();
    }
}
