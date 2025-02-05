package com.springboot.eCommerce.cronjob;

import com.springboot.eCommerce.service.InvalidTokenServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {
    @Autowired
    private InvalidTokenServiceInterface invalidTokenServiceInterface;

    @Scheduled(cron = "0 59 23 * * ?")
    public void removeInvalidTokenExpiryTime() {
        invalidTokenServiceInterface.deleteExpiryTimeToken();
        Date date = new Date();
        System.out.println(
                "Delete all expired tokens at: " + date);
    }
}
