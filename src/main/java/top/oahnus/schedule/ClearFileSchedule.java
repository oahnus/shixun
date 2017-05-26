package top.oahnus.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.oahnus.util.FileUploadDownloadUtil;

/**
 * Created by oahnus on 2017/5/12
 * 19:44.
 */
@Component
public class ClearFileSchedule {

    @Scheduled(cron = "0 0 */1 * * ?")
    public void clearFiles() {
        System.out.println(System.getProperty("user.dir"));
//        FileUploadDownloadUtil.removeRubbishUplaodFile("")
    }
}
