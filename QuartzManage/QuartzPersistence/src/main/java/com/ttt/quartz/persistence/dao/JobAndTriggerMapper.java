package com.ttt.quartz.persistence.dao;

import java.util.List;

import com.ttt.quartz.persistence.entity.JobAndTrigger;

public interface JobAndTriggerMapper {
	List<JobAndTrigger> getJobAndTriggerDetails();
}
