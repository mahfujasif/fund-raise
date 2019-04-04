package com.asif.ethereum.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor = @__())
public class CronJobServices {

    private final AuthenticationServices authenticationServices;

    private void addJob(String job) {

        String fileName= "/var/spool/cron/crontabs/"+System.getProperty("user.name");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName,true);
            fileWriter.write(job);//appends the string to the file
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(fileWriter!=null) {
                    fileWriter.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
            Runtime.getRuntime().exec("crontab /var/spool/cron/crontabs/"+System.getProperty("user.name"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scheduleEventTermination(Integer contractId, BigInteger eventId, BigInteger duration, String title) {

        System.out.println(contractId+" "+eventId+ " "+ duration + " " + title);

        LocalDateTime expireTime = LocalDateTime.now().plusMinutes(duration.intValue());

        String curlCommand =
                String.format("curl --header \"walletId:%s\" -G -X DELETE 'http://localhost:8081/v1/events' --data-urlencode \"contractId=%s\" --data-urlencode \"eventId=%s\"",
                        authenticationServices.getCurrentUserId(),
                        contractId,
                        eventId);

        String job = String.format("%s %s %s %s * %s\n",
                expireTime.getMinute(),
                expireTime.getHour(),
                expireTime.getDayOfMonth(),
                expireTime.getMonthValue(),
                curlCommand);

        this.addJob(job);
        System.out.println("Event termination scheduled for event "+title);

    }
}
