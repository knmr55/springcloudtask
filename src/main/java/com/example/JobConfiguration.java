package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {
 
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
 
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
   
    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
          .start(stepBuilderFactory.get("jobStep1")
          .tasklet(new Tasklet() {
             
              @Override
              public RepeatStatus execute(StepContribution contribution, 
                ChunkContext chunkContext) throws Exception {
                System.out.println("Job was run...........................................................");
                return RepeatStatus.FINISHED;
              }
       }).build()).build();
    }
}
