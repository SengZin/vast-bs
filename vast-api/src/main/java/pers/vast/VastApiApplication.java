package pers.vast;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by sengzin on 2017/9/16.
 */
@Slf4j
@SpringBootApplication
public class VastApiApplication {


    public static void main(String[] args) {
        Stopwatch sw = Stopwatch.createStarted();
        ApplicationContext ctx = SpringApplication.run(VastApiApplication.class, args);
        String[] beans = ctx.getBeanDefinitionNames();
        for (String bean : beans) {
            log.info("inspect bean {}.", bean);
        }
        log.info("inspected {} beans.", beans.length);
        log.info("------------ Vast api start success ------------ used {} ms", sw.elapsed(TimeUnit.MILLISECONDS));
    }

}
