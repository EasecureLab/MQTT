package com.wsn.nac.storage;

import com.wsn.nac.storage.common.ScreenEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: jia
 * Time: 2023/2/17  16:47
 * Description:
 * Version:
 */
@Component
@Slf4j
public class ScheduleTask {

    @Autowired
    MessageStore messageStore;

    /**
     * 每1h移除一次过老的历史记录
     * 最新数据：移除6小时之前的
     * 每小时触发一次
     */
    @Scheduled(fixedRate = 3600000)
    // @Scheduled(fixedRate = 1000)
    public void removeHistory(){
        log.info("移除6小时之前的数据");
        LocalDateTime time = LocalDateTime.now().minusHours(6);
        for (int i = 0; i <= 3; i++) {
            messageStore.removeByCollectionNameAndTime(ScreenEnum.select(i).toString(),time);
        }
        log.info("移除成功");
    }

    /**
     * 每24h移除一次过老的历史记录
     * 备份数据：移除一周之前的
     * 每天晚上两点触发
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void removeHistoryBackup(){
        log.info("移除一周之前的数据");
        LocalDateTime time = LocalDateTime.now().minusWeeks(1);
        for (int i = 0; i <= 3; i++) {
            messageStore.removeByCollectionNameAndTime(ScreenEnum.select(i).toString() + "backup",time);
        }
        log.info("移除成功");
    }

}
