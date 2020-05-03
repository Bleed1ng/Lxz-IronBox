//package com.bleeding.ironbox.config;
//
//import com.bleeding.ironbox.utils.QuestionScoreRefreshJob;
//import org.quartz.JobDataMap;
//import org.quartz.JobDetail;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.JobDetailFactoryBean;
//import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
//
//// @Configuration
//public class QuartzConfig {
//    // 刷新问题热度任务
//    @Bean
//    public JobDetailFactoryBean questionScoreRefreshJobDetail() {
//        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
//        factoryBean.setJobClass(QuestionScoreRefreshJob.class);
//        factoryBean.setName("questionScoreRefreshJob");
//        factoryBean.setGroup("ironboxJobGroup");
//        factoryBean.setDurability(true);
//        factoryBean.setRequestsRecovery(true);
//        return factoryBean;
//    }
//
//    @Bean
//    public SimpleTriggerFactoryBean questionScoreRefreshTrigger(JobDetail questionScoreRefreshJobDetail) {
//        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
//        factoryBean.setJobDetail(questionScoreRefreshJobDetail);
//        factoryBean.setName("questionScoreRefreshTrigger");
//        factoryBean.setGroup("ironboxTriggerGroup");
//        factoryBean.setRepeatInterval(1000 * 60 * 5);
//        factoryBean.setJobDataAsMap(new JobDataMap());
//        return factoryBean;
//    }
//}
