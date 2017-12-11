package com.ttt.quartz.persistence.service.impl;

import java.util.List;

import com.ttt.quartz.persistence.dao.JobAndTriggerMapper;
import com.ttt.quartz.persistence.service.IJobAndTriggerService;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ttt.quartz.persistence.entity.JobAndTrigger;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class JobAndTriggerImpl implements IJobAndTriggerService {
	private static final Logger logger = LoggerFactory.getLogger(JobAndTriggerImpl.class);

	@Autowired
	private JobAndTriggerMapper jobAndTriggerMapper;

	@Autowired @Qualifier("Scheduler")
	private Scheduler scheduler;

	public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
		for(JobAndTrigger trigger: list){
			TriggerKey key=TriggerKey.triggerKey(trigger.getTRIGGER_NAME(),trigger.getTRIGGER_GROUP());
			Trigger.TriggerState status= Trigger.TriggerState.NONE;
			try {
				status = scheduler.getTriggerState(key);
				trigger.setState(status);
			}catch (Exception e){
				logger.error("查询状态出错，"+e.getMessage());
			}
		}
		PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
		return page;
	}

}