package library.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class RentSchedulere {


    @Scheduled(cron = "1 * * * * *")
    public void sendMailOverdueToClient() {
        System.out.println(LocalDateTime.now());
    }
}
