package pers.vast;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by sengzin on 2017/9/16.
 */
@Slf4j
@SpringBootApplication
public class VastApiApplication {


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(VastApiApplication.class, args);
        String[] beans = ctx.getBeanDefinitionNames();
        for (String bean : beans) {
            log.info("inspect bean {}.", bean);
        }
        log.info("inspected {} beans.", beans.length);
        log.info("------------ Vast api start success ------------");
    }

}
