package com.eve.bookmarks.task;

import com.eve.bookmarks.entitys.po.ScheduleRecord;
import com.eve.bookmarks.entitys.vo.MailMsg;
import com.eve.bookmarks.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@EnableScheduling
@Service
@Transactional
public class ScheduleTaskConfig {

    Logger logger = LoggerFactory.getLogger(ScheduleTaskConfig.class);

    @Value("${spring.mail.username}")
    private String formAddress;

    @Value("${mail.toaddress}")
    private String toAddress;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    ScheduleService scheduleService;

//    @Scheduled(cron = "0 0/10 * * * ?")
    public void doTask() {
        logger.info("begin execute task ...");
        senMail();
    }

    public void senMail(){
        MailMsg mailMsg = scheduleService.queryDeadLineSchs();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(formAddress);
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject("提示邮件");
        StringBuilder stringBuilder = new StringBuilder("已过期仍未完成任务:\n");
        listToStr(mailMsg.getOverTimes(), stringBuilder);
        stringBuilder.append("快过期任务:\n");
        listToStr(mailMsg.getNearEnds(), stringBuilder);

        logger.info("mail message is :"+stringBuilder.toString());
        simpleMailMessage.setText(stringBuilder.toString());
        javaMailSender.send(simpleMailMessage);
    }

    String listToStr(List<ScheduleRecord> list, StringBuilder stringBuilder) {

        for (int i = 0; i < list.size(); i++) {
            ScheduleRecord scheduleRecord = list.get(i);
            stringBuilder.append((i+1) + ". " + scheduleRecord.getTitle()+"\n");
        }
        return stringBuilder.toString();
    }
}
