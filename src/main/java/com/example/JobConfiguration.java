package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.task.batch.listener.TaskBatchDao;
import org.springframework.cloud.task.batch.listener.TaskBatchExecutionListener;
import org.springframework.cloud.task.batch.listener.support.JdbcTaskBatchDao;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.cloud.task.configuration.TaskConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JobConfiguration {
 
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
 
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Primary
    @Bean(name="datasource1")
    @ConfigurationProperties(prefix="spring.datasource")
    public javax.sql.DataSource primaryDataSource() {
        return  DataSourceBuilder.create().build();
    }
    
    @Bean(name="datasource2")
    @ConfigurationProperties(prefix="spring.datasource1")
    public javax.sql.DataSource primaryDataSource1() {
        return  DataSourceBuilder.create().build();
    }
    
    @Bean
    public TaskConfigurer taskConfigurer() {
        return new DefaultTaskConfigurer(primaryDataSource());
    }
    
    @Bean
    public TaskBatchDao getBatchDao() {
        return new JdbcTaskBatchDao(primaryDataSource());
    }
    
   
    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
          .start(stepBuilderFactory.get("jobStep1")
          .tasklet(new Tasklet() {
             
              @Override
              public RepeatStatus execute(StepContribution contribution, 
                ChunkContext chunkContext) throws Exception {
                 
               System.out.println("Job was run");
               System.out.println("Job was run after sleep");
                return RepeatStatus.FINISHED;
              }
       }).build()).build();
    }
}
