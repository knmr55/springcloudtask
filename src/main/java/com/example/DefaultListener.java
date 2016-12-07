package com.example;

import org.springframework.cloud.task.batch.listener.TaskBatchDao;
import org.springframework.cloud.task.batch.listener.TaskBatchExecutionListener;
import org.springframework.stereotype.Component;
@Component
public class DefaultListener extends TaskBatchExecutionListener{

	public DefaultListener(TaskBatchDao taskBatchDao) {
		super(taskBatchDao);
		// TODO Auto-generated constructor stub
	}

}
